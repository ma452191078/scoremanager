package com.majy.scoremanager.mapper;

import com.majy.scoremanager.domain.PlayerInfo;

import java.util.List;

/**
 * Created by majingyuan on 2017/5/28.
 * 用户
 */

public interface PlayerInfoMapper {

    List<PlayerInfo> getAll();

    List<PlayerInfo> getPlayerListByGameId(String gameId);

    List<PlayerInfo> getPlayerList(PlayerInfo playerInfo);

    PlayerInfo getPlayerInfoById(String playerId);

    int insert(PlayerInfo playerInfo);

    int update(PlayerInfo playerInfo);

    int delete(String playerId);
}
