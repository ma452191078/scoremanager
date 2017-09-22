package com.majy.scoremanager.controller;

import com.majy.scoremanager.constant.AppConstant;
import com.majy.scoremanager.domain.UserInfo;
import com.majy.scoremanager.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 * Created by majingyuan on 2017/9/22.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping("/userLogin")
    public Map<String, String> login(UserInfo loginUser){
        Map<String, String> resultMap = new HashMap<>();
        String errCode, errMsg;

        if (loginUser != null && !"".equals(loginUser.getUserAccount())){
            if (!"".equals(loginUser.getUserPassword())){
                UserInfo userInfo = new UserInfo();
                userInfo = userInfoMapper.getUserInfoByAccount(loginUser.getUserAccount());

                if (!"".equals(userInfo.getUserId())){

                }
            }else {
                errCode = AppConstant.REQUEST_ERROR;
                errMsg = "用户密码不能为空";
            }
        }

        return resultMap;
    }
}
