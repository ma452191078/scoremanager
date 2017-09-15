package com.majy.scoremanager.controller;

import com.majy.scoremanager.domain.GameRoleInfo;
import com.majy.scoremanager.domain.ScoreRoleInfo;
import com.majy.scoremanager.mapper.GameRoleInfoMapper;
import com.majy.scoremanager.mapper.ScoreRoleInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by majingyuan on 2017/5/28.
 * 比赛
 */
@RestController
@RequestMapping("/scoreRole")
public class ScoreRoleController {

    @Autowired
    private ScoreRoleInfoMapper roleInfoMapper;

    @RequestMapping("/getScoreRoleList")
    public Map<String,Object> getScoreRoleList(ScoreRoleInfo scoreRoleInfo){
        Map<String,Object> map = new HashMap<String, Object>();
        List<ScoreRoleInfo> scoreRoleInfos = roleInfoMapper.getScoreRoleList(scoreRoleInfo);

        map.put("scoreList", scoreRoleInfos);
        return map;
    }

    
}
