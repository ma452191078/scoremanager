package com.majy.scoremanager.controller;

import com.majy.scoremanager.constant.AppConstant;
import com.majy.scoremanager.domain.JudgeInfo;
import com.majy.scoremanager.domain.ScoreRoleInfo;
import com.majy.scoremanager.mapper.JudgeInfoMapper;
import com.majy.scoremanager.mapper.ScoreRoleInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 评委
 * @author majingyuan
 * @date 2017/5/2
 */
@RestController
@RequestMapping("/judge")
public class JudgeController {

    @Autowired
    private JudgeInfoMapper judgeInfoMapper;

    /**
     * 查询裁判信息
     * @param judgeId
     * @return
     */
    @RequestMapping("/getJudgeInfo")
    public Map<String,Object> getJudgeInfo(String judgeId){
        Map<String,Object> map = new HashMap<String, Object>();
        JudgeInfo judgeInfo = judgeInfoMapper.getJudgeInfoById(judgeId);

        map.put("judgeInfo", judgeInfo);
        return map;
    }

    /**
     * 根据比赛查询裁判列表
     * @param gameId
     * @return
     */
    @RequestMapping("/getJudgeListByGame")
    public Map<String, Object> getJudgeListByGame(String gameId){
        Map<String,Object> map = new HashMap<String, Object>();
        List<JudgeInfo> judgeInfoList = judgeInfoMapper.getJudgeInfoByGameId(gameId);

        map.put("judgeInfoList", judgeInfoList);
        return map;
    }

    /**
     * 创建评委
     * @param judgeInfo
     * @return
     */
    @RequestMapping("/createJudge")
    public Map<String, Object> createJudge(JudgeInfo judgeInfo){
        Map<String,Object> map = new HashMap<String, Object>();
        int errFlag = AppConstant.DB_WRITE_FAILED;
        String errMsg = "信息创建失败，请重试";
        judgeInfo.setJudgeId(UUID.randomUUID().toString());
        if (judgeInfoMapper.insert(judgeInfo) > 0){
            errFlag = AppConstant.DB_WRITE_SUCCESS;
            errMsg = "创建成功";
        }
        map.put("judgeInfo", judgeInfo);
        map.put("errFlag", errFlag);
        map.put("errMsg", errMsg);
        return map;

    }
    
}
