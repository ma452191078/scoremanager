package com.majy.scoremanager.domain;

/**
 * Created by majingyuan on 2017/5/28.
 * 比赛类
 */
public class GameInfo {
    private String gameId;
    private String gameName;
    private String gameActive;
    private String startDate;
    private String endDate;
    private String addBy;
    private String gameOwner;
    private String gameDeleted;
    private String gameStatus;
    private String gameQr;
    private String gameUrl;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameActive() {
        return gameActive;
    }

    public void setGameActive(String gameActive) {
        this.gameActive = gameActive;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAddBy() {
        return addBy;
    }

    public void setAddBy(String addBy) {
        this.addBy = addBy;
    }

    public String getGameOwner() {
        return gameOwner;
    }

    public void setGameOwner(String gameOwner) {
        this.gameOwner = gameOwner;
    }

    public String getGameDeleted() {
        return gameDeleted;
    }

    public void setGameDeleted(String gameDeleted) {
        this.gameDeleted = gameDeleted;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public String getGameQr() {
        return gameQr;
    }

    public void setGameQr(String gameQr) {
        this.gameQr = gameQr;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }
}
