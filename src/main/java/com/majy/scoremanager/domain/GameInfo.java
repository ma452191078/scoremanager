package com.majy.scoremanager.domain;

import lombok.Data;

import java.util.List;


/**
 * Created by majingyuan on 2017/5/28.
 * 比赛类
 */
@Data
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
    private String gameRole;
    private List<GameRoleInfo> gameRoleInfoList;

    public GameInfo(){

    }

}
