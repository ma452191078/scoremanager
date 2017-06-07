package com.majy.scoremanager.util;

import com.majy.scoremanager.properties.ScoreProperties;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by majingyuan on 2017/6/4.
 * 微信工具类
 */
public class WeChatUtil {

    @Autowired
    private ScoreProperties properties;
    @Autowired
    private HttpsUtil httpsUtil;

    private String getAccessTokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=CORPID&corpsecret=SECRET";
    private String getUserTicketUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
    private String getUserInfoUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail?access_token=ACCESS_TOKEN";

    public String getAccessToken(){
        String accessToken = "";
        String requestUrl = getAccessTokenUrl.replace("CORPID", properties.getCorpID()).replace("SECRET", properties.getSecret());
        System.out.println("微信Token请求链接：" + requestUrl);
        JSONObject jsonObject = httpsUtil.httpRequest(requestUrl, "GET", null);
        System.out.println("微信Token请求结果：" + jsonObject.toString());
        if (jsonObject != null){
            accessToken = jsonObject.getString("access_token");
        }
        return accessToken;
    }

    public String getUserTicket(String accessToken, String code){
        String userTicket = "";
        String requestUrl = getUserTicketUrl.replace("ACCESS_TOKEN",accessToken).replace("CODE",code);
        System.out.println("微信UserTicket请求链接：" + requestUrl);
        JSONObject jsonObject = httpsUtil.httpRequest(requestUrl, "GET", null);
        System.out.println("微信UserTicket请求结果：" + jsonObject.toString());
        if (jsonObject != null){
            userTicket = jsonObject.getString("user_ticket");
        }
        return userTicket;
    }


    public String getGetUserName(String accessToken, String userTicket){
        String userName = "";
        String requestUrl = getUserInfoUrl.replace("ACCESS_TOKEN",accessToken);
        System.out.println("微信UserInfo请求链接：" + requestUrl);
        String param = "{\"user_ticket\" :\"" + userTicket + "\"}";
        JSONObject jsonObject = httpsUtil.httpRequest(requestUrl, "GET", param);
        System.out.println("微信UserInfo请求结果：" + jsonObject.toString());
        if (jsonObject != null){
            userName = jsonObject.getString("name");
        }
        return userName;
    }
}
