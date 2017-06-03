package com.majy.scoremanager.controller;

import com.majy.scoremanager.domain.GameInfo;
import com.majy.scoremanager.domain.PlayerInfo;
import com.majy.scoremanager.domain.ScoreInfo;
import com.majy.scoremanager.mapper.GameInfoMapper;
import com.majy.scoremanager.mapper.PlayerInfoMapper;
import com.majy.scoremanager.mapper.ScoreInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by majingyuan on 2017/5/28.
 * 参赛选手
 */
@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerInfoMapper playerInfoMapper;
    @Autowired
    private ScoreInfoMapper scoreInfoMapper;
    @Autowired
    private GameInfoMapper gameInfoMapper;

    /**
     * 多维度选手信息查询
     * @param playerInfo 参赛人信息作为查询条件
     * @return List<PlayerInfo> 选手列表
     */
    @RequestMapping("getPlayerList")
    public List<PlayerInfo> getPlayerList(PlayerInfo playerInfo){

        List<PlayerInfo> playerInfos = playerInfoMapper.getPlayerList(playerInfo);
        return playerInfos;
    }

    /**
     * 根据比赛ID查询对应选手列表，依据选手名次和选手序号排序
     * @param gameId 比赛id
     * @return List<PlayerInfo> 选手列表
     */
    @RequestMapping("/getPlayerListByGame")
    public Map<String, Object> getPlayerListByGame(@RequestParam("gameId") String gameId){
        Map<String, Object> param = new HashMap<>();
        List<PlayerInfo> playerInfos = null;
        GameInfo gameInfo = new GameInfo();
        if (gameId != null && !"".equals(gameId)){
            gameInfo = gameInfoMapper.getGameInfoById(gameId);
            playerInfos = playerInfoMapper.getPlayerListByGameId(gameId);
        }
        param.put("gameInfo", gameInfo);
        param.put("playerList", playerInfos);
        return param;
    }

    /**
     * 根据选手id获取选手完整信息
     * @param playerId 参赛人id
     * @return PlayerInfo 参赛选手信息
     */
    @RequestMapping("/getPlayerInfoById")
    public PlayerInfo getPlayerInfoById(@RequestParam("playerId") String playerId){
        if (playerId != null && !"".equals(playerId)) {
            PlayerInfo playerInfo = playerInfoMapper.getPlayerInfoById(playerId);
            return playerInfo;
        }else {
            return null;
        }
    }

    /**
     * 创建选手信息
     * @param playerInfo 参赛人信息
     * @return 返回执行结果
     */
    @RequestMapping("/addPlayerInfo")
    public Map<String, Object> addPlayerInfo(PlayerInfo playerInfo){
        Map<String, Object> param = new HashMap<>();
        String addFlag = "failed";
        String addMessage = "添加失败请稍后重试。";

        if (playerInfo != null && playerInfo.getPlayerName() != null
                && !"".equals(playerInfo.getPlayerName())){
            //检查序号是否存在
            PlayerInfo searchInfo = new PlayerInfo();
            searchInfo.setGameId(playerInfo.getGameId());
            searchInfo.setPlayerNum(playerInfo.getPlayerNum());
            List<PlayerInfo> searchList = playerInfoMapper.getPlayerList(searchInfo);
            if (searchList != null && searchList.size() > 0){
                addMessage = "出场顺序重复，请重新填写出场顺序。";
            }else {
                //无重复出场顺序执行下一步操作
                playerInfo.setPlayerId(UUID.randomUUID().toString());
                int result = playerInfoMapper.insert(playerInfo);
                if (result > 0){
                    addFlag = "success";
                    addMessage = "选手" + playerInfo.getPlayerName() + "创建成功。";
                    param.put("playerInfo", playerInfo);
                }
            }
        }

        param.put("flag", addFlag);
        param.put("message", addMessage);
        return param;
    }

    /**
     * 更新选手信息
     * @param playerInfo 参赛人信息
     * @return 返回执行结果
     */
    @RequestMapping("/updatePlayerInfo")
    public Map<String,String> updatePlayerInfo(PlayerInfo playerInfo){
        Map<String, String> param = new HashMap<>();
        String addFlag = "failed";
        String addMessage = "修改失败，请稍后重试。";

        if (playerInfo != null && playerInfo.getPlayerId() != null
                && !"".equals(playerInfo.getPlayerId())){
            int result = playerInfoMapper.update(playerInfo);
            if (result > 0){
                addFlag = "success";
                addMessage = "选手" + playerInfo.getPlayerName() + "修改成功。";
            }
        }

        param.put("flag", addFlag);
        param.put("message", addMessage);
        return param;
    }

    /**
     * 选手评分终止，计算选手最终得分
     * @param playerId 参赛人id
     * @return 返回执行结果
     */
    @RequestMapping("/killPlayerInfo")
    public Map<String,String> killPlayerInfo(@RequestParam("playerId") String playerId){
        Map<String, String> param = new HashMap<>();
        String addFlag = "failed";
        String addMessage = "评分终止失败，请稍后重试。";
        BigDecimal sumScore = new BigDecimal(0.00); //总得分
        BigDecimal avgScore = new BigDecimal(0.00); //平均分

        if (playerId != null && !"".equals(playerId)){
            //选手得分计算方法待完善
            List<ScoreInfo> scoreInfos = scoreInfoMapper.getScoreListByPlayer(playerId);
            if (scoreInfos != null && scoreInfos.size() > 0){
                if (scoreInfos.size() > 2) {
                    scoreInfos.remove(0);   //去除最低分
                    scoreInfos.remove(scoreInfos.size() - 1); //去掉最高分
                }

                //求得总分
                for (ScoreInfo item : scoreInfos){
                    sumScore = sumScore.add(item.getScoreValue());
                }
                //计算平均分
                avgScore = sumScore.divide(new BigDecimal(scoreInfos.size()),2,BigDecimal.ROUND_HALF_UP);

            }
            //选手状态变更
            PlayerInfo playerInfo = new PlayerInfo();
            playerInfo.setPlayerId(playerId);
            playerInfo.setPlayerActive("1");
            playerInfo.setPlayerSum(sumScore);
            playerInfo.setPlayerAverage(avgScore);
            int result = playerInfoMapper.update(playerInfo);
            if (result > 0){
                playerInfo = playerInfoMapper.getPlayerInfoById(playerId);
                PlayerInfo searchInfo = new PlayerInfo();
                searchInfo.setPlayerActive("1");
                searchInfo.setGameId(playerInfo.getGameId());
                List<PlayerInfo> playerInfoList = playerInfoMapper.getPlayerList(searchInfo);
                for (int i = 0; i < playerInfoList.size(); i++){
                    playerInfoList.get(i).setPlayerRanking(i + 1);
                    playerInfoMapper.update(playerInfoList.get(i));
                }
                addFlag = "success";
                addMessage = "选手" + playerInfo.getPlayerName() + "评分已终止。";
            }

        }

        param.put("flag", addFlag);
        param.put("message", addMessage);
        return param;
    }

    @RequestMapping("/delPlayer")
    public Map<String, Object> delPlayer(@RequestParam("playerId") String playerId){
        Map<String, Object> param = new HashMap<>();
        String addFlag = "failed";

        if (playerId != null && !"".equals(playerId)){
            int result = playerInfoMapper.delete(playerId);
            if (result > 0){
                addFlag = "success";
            }
        }
        param.put("flag", addFlag);
        return param;
    }

}
