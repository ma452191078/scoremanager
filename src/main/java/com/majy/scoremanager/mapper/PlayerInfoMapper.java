package com.majy.scoremanager.mapper;

import com.majy.scoremanager.domain.PlayerInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by majingyuan on 2017/5/28.
 * 用户
 */

public interface PlayerInfoMapper {

    List<PlayerInfo> getAll();

    List<PlayerInfo> getPlayerListByGameId(String gameId);

    List<PlayerInfo> getPlayerList(PlayerInfo playerInfo);

    List<PlayerInfo> getAvgListByPlayer(String gameId);

    List<PlayerInfo> getScoreListByGameId(String gameId);

    PlayerInfo getPlayerInfoById(String playerId);

    List<PlayerInfo> getPlayerListByJudge(Map param);

    int insert(PlayerInfo playerInfo);

    int update(PlayerInfo playerInfo);

    int delete(String playerId);
}
