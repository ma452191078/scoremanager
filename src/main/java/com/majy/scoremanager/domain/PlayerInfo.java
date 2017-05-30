package com.majy.scoremanager.domain;

import java.math.BigDecimal;

/**
 * Created by majingyuan on 2017/5/28.
 * 参赛选手类
 */
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

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(Integer playerNum) {
        this.playerNum = playerNum;
    }

    public String getPlayerDepartment() {
        return playerDepartment;
    }

    public void setPlayerDepartment(String playerDepartment) {
        this.playerDepartment = playerDepartment;
    }

    public BigDecimal getPlayerSum() {
        return playerSum;
    }

    public void setPlayerSum(BigDecimal playerSum) {
        this.playerSum = playerSum;
    }

    public BigDecimal getPlayerAverage() {
        return playerAverage;
    }

    public void setPlayerAverage(BigDecimal playerAverage) {
        this.playerAverage = playerAverage;
    }

    public Integer getPlayerRanking() {
        return playerRanking;
    }

    public void setPlayerRanking(Integer playerRanking) {
        this.playerRanking = playerRanking;
    }

    public String getPlayerImg() {
        return playerImg;
    }

    public void setPlayerImg(String playerImg) {
        this.playerImg = playerImg;
    }

    public String getPlayerAddBy() {
        return playerAddBy;
    }

    public void setPlayerAddBy(String playerAddBy) {
        this.playerAddBy = playerAddBy;
    }

    public String getPlayerDeleted() {
        return playerDeleted;
    }

    public void setPlayerDeleted(String playerDeleted) {
        this.playerDeleted = playerDeleted;
    }

    public String getPlayerActive() {
        return playerActive;
    }

    public void setPlayerActive(String playerActive) {
        this.playerActive = playerActive;
    }
}
