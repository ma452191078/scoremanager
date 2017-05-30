package com.majy.scoremanager.mapper;

import com.majy.scoremanager.domain.ScoreInfo;

import java.util.List;

/**
 * Created by majingyuan on 2017/5/28.
 * 评分
 */

public interface ScoreInfoMapper {

    List<ScoreInfo> getAll();

    List<ScoreInfo> getScoreList(ScoreInfo scoreInfo);

    List<ScoreInfo> getScoreListByPlayer(String playerId);

    ScoreInfo getScoreInfoById(String scoreId);

    int insert(ScoreInfo scoreInfo);

    int update(ScoreInfo scoreInfo);

    int delete(String scoreId);
}
