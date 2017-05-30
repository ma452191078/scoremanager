package com.majy.scoremanager.mapper;

import com.majy.scoremanager.domain.GameInfo;

import java.util.List;

/**
 * Created by majingyuan on 2017/5/28.
 * 用户
 */

public interface GameInfoMapper {

    List<GameInfo> getAll();

    List<GameInfo> getGameList(GameInfo gameInfo);

    GameInfo getGameInfoById(String gameId);

    int insert(GameInfo gameInfo);

    int update(GameInfo gameInfo);

    int delete(String gameId);
}
