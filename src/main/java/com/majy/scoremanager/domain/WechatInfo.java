package com.majy.scoremanager.domain;

/**
 * Created by majingyuan on 2017/6/4.
 * 微信企业号相关属性
 */
public class WechatInfo {
    private int id;
    //微信token
    private String accessToken;
    //token有效期
    private Long expiresIn;
    //添加时间
    private Long addTime;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
