package com.majy.scoremanager.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by majingyuan on 2017/9/13.
 * 评分项
 */
@Data
public class GameRoleInfo {

//    规则ID
    private String roleId;
//    规则序号
    private Integer roleIndex;
//    规则名称
    private String roleName;
//    规则分值
    private BigDecimal roleScore;
//    比赛ID
    private String gameId;
}
