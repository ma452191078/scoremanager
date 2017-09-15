package com.majy.scoremanager.controller;

import com.majy.scoremanager.domain.GameInfo;
import com.majy.scoremanager.domain.GameRoleInfo;
import com.majy.scoremanager.mapper.GameInfoMapper;
import com.majy.scoremanager.mapper.GameRoleInfoMapper;
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
@RequestMapping("/gameRole")
public class GameRoleController {

    @Autowired
    private GameRoleInfoMapper gameInfoMapper;

    @RequestMapping("/getGameRoleList")
    public Map<String,Object> getGameList(@RequestParam("gameId") String gameId){
        Map<String,Object> map = new HashMap<String, Object>();
        List<GameRoleInfo> gameRoleList = gameInfoMapper.getGameRoleListByGame(gameId);

        map.put("gameRoleList", gameRoleList);
        return map;
    }


}
