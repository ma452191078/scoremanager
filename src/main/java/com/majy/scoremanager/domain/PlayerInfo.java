package com.majy.scoremanager.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by majingyuan on 2017/5/28.
 * 参赛选手类
 */
@Data
public class PlayerInfo {
    private String playerId;
    private String gameId;
    private String playerName;
    private Integer playerNum;
    private String playerDepartment;
    private BigDecimal playerSum;
    private BigDecimal playerAverage;
    private Integer playerRanking;
    private String playerImg;
    private String playerAddBy;
    private String playerDeleted;
    private String playerActive;

    public PlayerInfo(){

    }
}
