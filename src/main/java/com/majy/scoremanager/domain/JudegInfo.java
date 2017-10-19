package com.majy.scoremanager.domain;

import lombok.Data;

/**
 * 评委类
 * @Author majingyuan
 * @Date Create in 2017/10/19 23:44
 */
@Data
public class JudegInfo {
    /**
     * 评委ID
     */
    private String judgeId;
    /**
     * 评委姓名
     */
    private String judegName;
    /**
     * 比赛ID
     */
    private String gameId;
}
