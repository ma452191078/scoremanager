package com.majy.scoremanager.configuration;

import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.WxCpInMemoryConfigStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author majingyuan
 * @Date Create in 2017/11/23 17:18
 */
@Component
public class WechatAccountConfig {
    @Autowired
    WechatConfig wechatConfig;

    @Bean
    public WxCpService wxCpService(){
        WxCpService wxCpService = new WxCpServiceImpl();
        wxCpService.setWxCpConfigStorage(wxCpConfigStorage());
        return wxCpService;
    }

    @Bean
    public WxCpConfigStorage wxCpConfigStorage(){
        WxCpInMemoryConfigStorage wxCpInMemoryConfigStorage = new WxCpInMemoryConfigStorage();
        wxCpInMemoryConfigStorage.setCorpId(wechatConfig.getCorpId());
        wxCpInMemoryConfigStorage.setAgentId(wechatConfig.getAgentId());
        wxCpInMemoryConfigStorage.setCorpSecret(wechatConfig.getSecret());
        return wxCpInMemoryConfigStorage;

    }
}
