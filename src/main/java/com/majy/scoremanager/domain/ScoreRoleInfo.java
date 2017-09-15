package com.majy.scoremanager.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by majingyuan on 2017/9/13.
 * 评分项
 */
@Data
public class ScoreRoleInfo {

//    得分ID
    private String scoreId;
//    规则分值
    private BigDecimal scoreValue;
//    选手ID
    private String playerId;
//    评委ID
    private String judgeId;
//    规则ID
    private String roleId;

}
