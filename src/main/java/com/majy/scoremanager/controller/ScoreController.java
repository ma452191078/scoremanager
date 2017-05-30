package com.majy.scoremanager.controller;

import com.majy.scoremanager.domain.PlayerInfo;
import com.majy.scoremanager.domain.ScoreInfo;
import com.majy.scoremanager.mapper.PlayerInfoMapper;
import com.majy.scoremanager.mapper.ScoreInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    private PlayerInfoMapper playerInfoMapper;

    @RequestMapping("/getScoreListByPlayer")
    public List<ScoreInfo> getScoreListByPlayer(String playerId){
        List<ScoreInfo> scoreInfos = null;
        if (playerId != null && !"".equals(playerId))
            scoreInfos = scoreInfoMapper.getScoreListByPlayer(playerId);
        return scoreInfos;
    }

    /**
     * 创建评分
     * @param scoreInfo
     * @return
     */
    @RequestMapping("/addScore")
    public Map<String,String> addScore(ScoreInfo scoreInfo) {

        Map<String, String> param = new HashMap<>();
        String addFlag = "failed";
        String addMessage = "~~~~(>_<)~~~~评分提交失败了，麻烦您再重试一次吧。";

        if (scoreInfo != null && scoreInfo.getScoreValue() != null){
            PlayerInfo playerInfo = playerInfoMapper.getPlayerInfoById(scoreInfo.getPlayerId());
            if ("0".equals(playerInfo.getPlayerActive())){
                scoreInfo.setScoreId(UUID.randomUUID().toString());
                int result = scoreInfoMapper.insert(scoreInfo);
                if (result > 0){
                    addFlag = "success";
                    addMessage = "您的评分已提交，感谢您的参与。";
                }
            }else {
                addFlag = "failed";
                addMessage = "选手评分已停止，您不能提交评分。";
            }
        }

        param.put("flag", addFlag);
        param.put("message", addMessage);
        return param;
    }


}
