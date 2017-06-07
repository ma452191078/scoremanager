package com.majy.scoremanager.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by majingyuan on 2017/6/3.
 * 读取配置文件中的自定义配置
 */
@Component
@ConfigurationProperties(prefix = "myscore")
public class ScoreProperties {
    //图片保存路径
    private String imgPath;
    //图片存储目录
    private String imgDictory;
    //微信企业号相关
    private String CorpID;
    private String Secret;
    //token有效时间
    private long expiresIn;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgDictory() {
        return imgDictory;
    }

    public void setImgDictory(String imgDictory) {
        this.imgDictory = imgDictory;
    }

    public String getCorpID() {
        return CorpID;
    }

    public void setCorpID(String corpID) {
        CorpID = corpID;
    }

    public String getSecret() {
        return Secret;
    }

    public void setSecret(String secret) {
        Secret = secret;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
