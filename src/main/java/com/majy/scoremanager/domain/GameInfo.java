package com.majy.scoremanager.domain;

import lombok.Data;

import java.util.List;


/**
 * @author majingyuan
 * @date 2017/5/28
 * Created by majingyuan on 2017/5/28.
 * 比赛类
 */
@Data
public class GameInfo {
    /**
     * 比赛ID
     */
    private String gameId;
    /**
     * 比赛名称
     */
    private String gameName;
    /**
     * 激活标识（0可用，1不可用）
     */
    private String gameActive;
    /**
     * 开始时间
     */
    private String startDate;
    /**
     * 结束时间
     */
    private String endDate;
    /**
     * 创建人
     */
    private String addBy;
    /**
     * 主办单位
     */
    private String gameOwner;
    /**
     * 删除标识（0未删除，1删除）
     */
    private String gameDeleted;
    /**
     *
     */
    private String gameStatus;
    /**
     * 二维码
     */
    private String gameQr;
    /**
     * 链接地址
     */
    private String gameUrl;
    /**
     * 比赛规则ID
     */
    private String gameRole;

    /**
     * 评委实名模式，0启用，1不启用
     */
    private String realNameFlag;

    /**
     * 修改评分模式，0启用，1启用
     */
    private String changeScoreFlag;

    private List<GameRoleInfo> gameRoleInfoList;


}
