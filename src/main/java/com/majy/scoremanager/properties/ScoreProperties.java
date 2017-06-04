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
    private String imgPath;
    private String imgDictory;

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
}
