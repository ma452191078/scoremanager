package com.majy.scoremanager.mapper;

import com.majy.scoremanager.domain.GameRoleInfo;

import java.util.List;

/**
 * Created by majingyuan on 2017/5/28.
 * 比赛评分规则
 */

public interface GameRoleInfoMapper {

    List<GameRoleInfo> getAll();

    List<GameRoleInfo> getGameRoleList(GameRoleInfo gameInfo);

    List<GameRoleInfo> getGameRoleListByGame(String gameId);

    GameRoleInfo getGameRoleInfoById(String roleId);

    int insert(GameRoleInfo gameInfo);

    int update(GameRoleInfo gameInfo);

    int delete(String roleId);

    int deleteAllByGame(String gameId);
}
