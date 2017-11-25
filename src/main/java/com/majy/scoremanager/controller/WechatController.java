package com.majy.scoremanager.controller;

import com.majy.scoremanager.configuration.WechatConfig;
import com.majy.scoremanager.constant.AppConstant;
import groovy.util.logging.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpOAuth2Service;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author majingyuan
 * @date Create in 2017/11/23 16:50
 */

@RestController
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    WxCpService wxCpService;
    @Autowired
    WechatConfig wechatConfig;

    @RequestMapping("/authorize")
    public Map<String, String> authorize(@RequestParam("gameId") String gameId){
        String redirectUrl = wechatConfig.getRedirectUrl();
        redirectUrl = redirectUrl.replace("GAMEID", gameId);
        WxCpOAuth2Service wxCpOAuth2Service = wxCpService.getOauth2Service();
        String url = wxCpOAuth2Service.buildAuthorizationUrl(redirectUrl, null);
        Map<String, String> param = new HashMap<>();
        param.put("url", url);
        return param;
    }

    @RequestMapping("/getUserInfo")
    public String getUserInfo(@RequestParam("code") String code) throws WxErrorException {
        WxCpOAuth2Service wxCpOAuth2Service = wxCpService.getOauth2Service();
        String[] res = wxCpOAuth2Service.getUserInfo(code);
        WxCpUser wxCpUser = wxCpService.getUserService().getById(res[0]);
        return wxCpUser.toJson();
    }
}
