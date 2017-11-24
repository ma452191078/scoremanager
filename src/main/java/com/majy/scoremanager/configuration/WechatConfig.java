package com.majy.scoremanager.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author majingyuan
 * @Date Create in 2017/11/23 16:55
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechatConfig")
public class WechatConfig {
    private String corpId;
    private Integer agentId;
    private String secret;

}
