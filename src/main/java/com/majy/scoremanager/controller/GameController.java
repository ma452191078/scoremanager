package com.majy.scoremanager.controller;

import com.majy.scoremanager.domain.GameInfo;
import com.majy.scoremanager.domain.WechatInfo;
import com.majy.scoremanager.mapper.GameInfoMapper;
import com.majy.scoremanager.mapper.WechatInfoMapper;
import com.majy.scoremanager.util.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by majingyuan on 2017/5/28.
 * 比赛
 */
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameInfoMapper gameInfoMapper;
    @Autowired
    private WechatInfoMapper wechatInfoMapper;

    @RequestMapping("/getGameList")
    public Map<String,Object> getGameList(GameInfo gameInfo){
        Map<String,Object> map = new HashMap<String, Object>();
        if (gameInfo == null)
            gameInfo = new GameInfo();
        List<GameInfo> gameInfoList = gameInfoMapper.getGameList(gameInfo);
        if (gameInfoList != null && gameInfoList.size() > 0){
            for (int i = 0; i < gameInfoList.size(); i ++){
                switch (gameInfoList.get(i).getGameActive()){
                    case "0":
                        gameInfoList.get(i).setGameStatus("进行中");
                        break;
                    case "1":
                        gameInfoList.get(i).setGameStatus("已结束");
                        break;
                    default:
                        break;
                }
            }
        }
        map.put("gameList", gameInfoList);
        return map;
    }

    /**
     * 比赛ID查询比赛详情
     * @param gameId 比赛id
     * @return
     */
    @RequestMapping("/getGameInfoById")
    public GameInfo getGameInfoById(@RequestParam("gameId") String gameId){
        GameInfo gameInfo = gameInfoMapper.getGameInfoById(gameId);
        return gameInfo;
    }

    /**
     * 更新比赛信息
     * @param gameInfo 比赛信息
     * @return
     */
    @RequestMapping("/updateGameInfo")
    public Map<String, String> updateGameInfo(GameInfo gameInfo){
        Map<String, String> param = new HashMap<>();
        String addFlag = "failed";
        String addMessage = "修改失败请稍后重试。";

        if (gameInfo != null){
            if (gameInfo.getGameId() == null || "".equals(gameInfo.getGameId())){
                //gameId不存在创建比赛
                gameInfo.setGameId(UUID.randomUUID().toString());
                int result = gameInfoMapper.insert(gameInfo);
                if (result > 0) {
                    addFlag = "success";
                    addMessage = "比赛" + gameInfo.getGameName() + "创建成功，请为此次比赛添加选手。";
                }
            } else {
                //gameId存在修改比赛
                int result = gameInfoMapper.update(gameInfo);
                if (result > 0){
                    addFlag = "success";
                    addMessage = "比赛" + gameInfo.getGameName() + "修改成功。";
                }
            }

        }
        param.put("gameId",gameInfo.getGameId());
        param.put("flag", addFlag);
        param.put("message", addMessage);
        return param;
    }

    /**
     * 终止比赛，改变比赛状态，写入截止时间
     * @param gameId 比赛id
     * @return
     */
    @RequestMapping("/killGame")
    public Map<String, String> killGame(@RequestParam("gameId") String gameId){
        Map<String, String> param = new HashMap<>();
        String addFlag = "failed";
        String addMessage = "比赛停止失败，请重试。";
        if (gameId != null && !"".equals(gameId)){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            GameInfo gameInfo = new GameInfo();
            gameInfo.setGameId(gameId);
            gameInfo.setEndDate(df.format(new Date()));
            gameInfo.setGameActive("1");

            int result = gameInfoMapper.update(gameInfo);
            if (result > 0){
                addFlag = "success";
                addMessage = "比赛已停止。";
            }
        }
        param.put("flag", addFlag);
        param.put("message", addMessage);
        return param;
    }

    /**
     * 获取评委id
     * @param gameId 比赛id
     * @return 查询结果
     */
    @RequestMapping("/getGameJudgeId")
    public Map<String, String> getGameJudgeId(@RequestParam("gameId") String gameId,
                                              @RequestParam("code") String code){
        Map<String, String> param = new HashMap<>();
        String getFlag = "failed";
        GameInfo gameInfo;
        String judgeId = "";
        String userName = null;
        gameInfo = gameInfoMapper.getGameInfoById(gameId);
        if (gameInfo != null){
            String accessToken = null;
            WechatInfo wechatInfo = wechatInfoMapper.getWechatInfo();
            if (wechatInfo != null){
                accessToken = wechatInfo.getAccessToken();
                Date now = new Date();
                if (now.getTime() - wechatInfo.getAddTime() > 7200){
                    accessToken = null;
                }
            }
            if (accessToken == null || "".equals(accessToken)){
                WeChatUtil weChatUtil = new WeChatUtil();
                accessToken = weChatUtil.getAccessToken();
                if (!"".equals(accessToken)){
                    wechatInfo.setAccessToken(accessToken);
                    wechatInfo.setAddTime(new Date().getTime());
                    if (wechatInfo.getId() > 0){
                        wechatInfoMapper.update(wechatInfo);
                    }else {
                        wechatInfoMapper.insert(wechatInfo);
                    }
                    String userTicket = weChatUtil.getUserTicket(accessToken,code);
                    if (!"".equals(userTicket)){
                        userName = weChatUtil.getGetUserName(accessToken,userTicket);
                    }
                }
            }

            judgeId = UUID.randomUUID().toString();
            getFlag = "success";
        }
        param.put("flag", getFlag);
        param.put("judgeId", judgeId);
        param.put("userName", userName);
        return param;
    }

}
