package com.majy.scoremanager.domain;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by majingyuan on 2017/5/28.
 * 评分类
 */
@Data
public class ScoreInfo {
    private String scoreId;
    private BigDecimal scoreValue;
    private String gameId;
    private String playerId;
    private String addTime;
    //排序标志
    private String orderFlag;
    private String judgeId;
    private String judgeName;
    private List<ScoreRoleInfo> scoreRoleInfoList;

    public ScoreInfo(){

    }
}
