package com.majy.scoremanager.controller;

import com.majy.scoremanager.constant.AppConstant;
import com.majy.scoremanager.domain.UserInfo;
import com.majy.scoremanager.mapper.UserInfoMapper;
import com.majy.scoremanager.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 登录
 * Created by majingyuan on 2017/9/22.
 */
@SuppressWarnings("AlibabaClassMustHaveAuthor")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping("/userLogin")
    public Map<String, String> login(UserInfo loginUser){
        Map<String, String> resultMap = new HashMap<>();
        String errCode = "", errMsg = "";

        if (loginUser != null && !"".equals(loginUser.getUserAccount())){
            if (!"".equals(loginUser.getUserPassword())){
                UserInfo userInfo = userInfoMapper.getUserInfoByAccount(loginUser.getUserAccount());
                if (userInfo != null && !"".equals(userInfo.getUserId())
                        && userInfo.getUserPassword().equals(MD5Util.encode(loginUser.getUserPassword()))){
                    UserInfo updateUser = new UserInfo();
                    updateUser.setUserId(userInfo.getUserId());
                    updateUser.setUserToken(UUID.randomUUID().toString());
                    userInfoMapper.update(updateUser);
                    userInfo.setUserToken(updateUser.getUserToken());
                    errCode = AppConstant.REQUEST_SUCCESS;
                    resultMap.put("userName", userInfo.getUserName());
                    resultMap.put("userId", userInfo.getUserId());
                    resultMap.put("userToken", userInfo.getUserToken());

                }else {
                    errCode = AppConstant.REQUEST_ERROR;
                    errMsg = "用户名或密码错误";
                }
            }else {
                errCode = AppConstant.REQUEST_ERROR;
                errMsg = "用户密码不能为空";
            }
        }
        resultMap.put("errCode", errCode);
        resultMap.put("errMsg", errMsg);
        return resultMap;
    }

    @RequestMapping("/checkToken")
    public Map<String, String> checkToken(UserInfo loginUser){
        Map<String, String> resultMap = new HashMap<>();
        String errCode = "";
        if (loginUser != null && !"".equals(loginUser.getUserToken())){
            UserInfo userInfo = userInfoMapper.getUserInfoByToken(loginUser.getUserToken());
            if (userInfo != null && userInfo.getUserId().equals(loginUser.getUserId())){
                errCode = AppConstant.REQUEST_SUCCESS;
            }else {
                errCode = AppConstant.REQUEST_ERROR;
            }
        }else {
            errCode = AppConstant.REQUEST_ERROR;
        }

        resultMap.put("errCode", errCode);
        return resultMap;
    }
}
