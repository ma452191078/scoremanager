package com.majy.scoremanager.controller;

import com.majy.scoremanager.constant.AppConstant;
import com.majy.scoremanager.domain.*;
import com.majy.scoremanager.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by majingyuan on 2017/5/28.
 * 评分
 */
@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreInfoMapper scoreInfoMapper;
    @Autowired
    private PlayerInfoMapper playerInfoMapper;
    @Autowired
    private GameInfoMapper gameInfoMapper;
    @Autowired
    private ScoreRoleInfoMapper scoreRoleInfoMapper;
    @Autowired
    private JudgeInfoMapper judgeInfoMapper;

    @RequestMapping("/getScoreListByPlayer")
    public List<ScoreInfo> getScoreListByPlayer(String playerId){
        List<ScoreInfo> scoreInfos = null;
        if (playerId != null && !"".equals(playerId)) {
            scoreInfos = scoreInfoMapper.getScoreListByPlayer(playerId);
        }

        if (scoreInfos != null && scoreInfos.size() > 0){
            List<ScoreRoleInfo> scoreRoleInfoList = null;
            JudgeInfo judgeSearchInfo = new JudgeInfo();
            for (int i = 0; i < scoreInfos.size(); i++) {
                //查询评委信息
                judgeSearchInfo.setJudgeId(scoreInfos.get(i).getJudgeId());
                judgeSearchInfo.setGameId(scoreInfos.get(i).getGameId());
                List<JudgeInfo> judgeList = judgeInfoMapper.getList(judgeSearchInfo);
                if (judgeList != null && judgeList.size() > 0){
                    scoreInfos.get(i).setJudgeName(judgeList.get(0).getJudgeName());
                }
                ScoreRoleInfo searchInfo = new ScoreRoleInfo();
                searchInfo.setPlayerId(scoreInfos.get(i).getPlayerId());
                searchInfo.setJudgeId(scoreInfos.get(i).getJudgeId());
                scoreRoleInfoList = scoreRoleInfoMapper.getScoreRoleList(searchInfo);
                if (scoreRoleInfoList != null && scoreRoleInfoList.size() > 0){
                    scoreInfos.get(i).setScoreRoleInfoList(scoreRoleInfoList);
                }
            }
        }
        return scoreInfos;
    }

    /**
     * 检查评委是否已经打分
     * @param scoreInfo
     * @return
     */
    @RequestMapping("/checkScoreByJudgeId")
    public Map<String,String> checkScoreByJudgeId(ScoreInfo scoreInfo){
        Map<String, String> param = new HashMap<>();
        String checkFlag = "failed";
        GameInfo gameInfo = gameInfoMapper.getGameInfoById(scoreInfo.getGameId());
        if (gameInfo != null && AppConstant.FLAG_DISABLE.equals(gameInfo.getChangeScoreFlag())){
            List<ScoreInfo> scoreList = scoreInfoMapper.getScoreList(scoreInfo);
            if (scoreList != null && scoreList.size() > 0){
                checkFlag = "success";
            }
        }

        param.put("flag", checkFlag);
        return param;
    }

    /**
     * 检查评委是否已经打分
     * @param scoreInfo
     * @return
     */
    @RequestMapping("/checkScoreByJudgeIdReact")
    public Map<String,String> checkScoreByJudgeIdReact(@RequestBody ScoreInfo scoreInfo){
        return checkScoreByJudgeId(scoreInfo);
    }

    /**
     * 创建评分
     * @param scoreInfo
     * @return
     */
    @RequestMapping("/addScore")
    public Map<String,String> addScore(@RequestBody ScoreInfo scoreInfo) {

        Map<String, String> param = new HashMap<>();
        String addFlag = "failed";
        String addMessage = "~~~~(>_<)~~~~评分提交失败了，麻烦您再重试一次吧。";
        BigDecimal tmpScoreValue = new BigDecimal(0);
        if (scoreInfo != null && scoreInfo.getScoreRoleInfoList() != null && scoreInfo.getScoreRoleInfoList().size() > 0){
            GameInfo gameInfo = gameInfoMapper.getGameInfoById(scoreInfo.getGameId());
            if ("0".equals(gameInfo.getGameActive())){
                PlayerInfo playerInfo = playerInfoMapper.getPlayerInfoById(scoreInfo.getPlayerId());
                if ("0".equals(playerInfo.getPlayerActive())){
                    //创建评分明细
                    if (scoreInfo.getScoreRoleInfoList() != null && scoreInfo.getScoreRoleInfoList().size() > 0){

                        //删除原打分记录
                        ScoreRoleInfo deleteInfo = new ScoreRoleInfo();
                        deleteInfo.setJudgeId(scoreInfo.getJudgeId());
                        deleteInfo.setPlayerId(scoreInfo.getPlayerId());
                        scoreRoleInfoMapper.delete(deleteInfo);
                        //=====================================
                        for (ScoreRoleInfo scoreRoleInfo : scoreInfo.getScoreRoleInfoList()) {
                            scoreRoleInfo.setScoreId(UUID.randomUUID().toString());
                            scoreRoleInfo.setPlayerId(scoreInfo.getPlayerId());
                            scoreRoleInfo.setJudgeId(scoreInfo.getJudgeId());
                            scoreRoleInfoMapper.insert(scoreRoleInfo);
                            tmpScoreValue = tmpScoreValue.add(scoreRoleInfo.getScoreValue());
                        }
                    }
                    //删除原总分
                    ScoreInfo deleteScore = new ScoreInfo();
                    deleteScore.setJudgeId(scoreInfo.getJudgeId());
                    deleteScore.setPlayerId(scoreInfo.getPlayerId());
                    deleteScore.setGameId(scoreInfo.getGameId());
                    scoreInfoMapper.delete(deleteScore);
                    //创建总分
                    scoreInfo.setScoreId(UUID.randomUUID().toString());
                    scoreInfo.setScoreValue(tmpScoreValue);
                    int result = scoreInfoMapper.insert(scoreInfo);
                    if (result > 0){
                        addFlag = "success";
                        addMessage = "您的评分已提交，感谢您的参与。";
                    }
                }else {
                    addFlag = "failed";
                    addMessage = "选手评分已停止，您不能提交评分。";
                }
            }else {
                addFlag = "failed";
                addMessage = "比赛已停止，您不能提交评分。";
            }
        }

        param.put("flag", addFlag);
        param.put("message", addMessage);
        return param;
    }


}
