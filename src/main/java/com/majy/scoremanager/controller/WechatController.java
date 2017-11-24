package com.majy.scoremanager.controller;

import com.majy.scoremanager.configuration.WechatAccountConfig;
import com.majy.scoremanager.constant.AppConstant;
import groovy.util.logging.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpOAuth2Service;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author majingyuan
 * @Date Create in 2017/11/23 16:50
 */

@RestController
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    WxCpService wxCpService;

    @RequestMapping("/authorize")
    public String authorize(){
        WxCpOAuth2Service wxCpOAuth2Service = wxCpService.getOauth2Service();
        String url = wxCpOAuth2Service.buildAuthorizationUrl(AppConstant.REDIRECT_URI, null);
        return url;
    }

    @RequestMapping("/getUserInfo")
    public String getUserInfo(@RequestParam("code") String code) throws WxErrorException {
        WxCpOAuth2Service wxCpOAuth2Service = wxCpService.getOauth2Service();
        String[] res = wxCpOAuth2Service.getUserInfo(code);
        WxCpUser wxCpUser = wxCpService.getUserService().getById(res[0]);
        return wxCpUser.toJson();
    }
}
