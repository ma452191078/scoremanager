package com.majy.scoremanager.controller;

import com.majy.scoremanager.domain.UserInfo;
import com.majy.scoremanager.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by majingyuan on 2017/5/28.
 *
 */
@RestController
@RequestMapping("/user")
public class UserContorller {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping("/getUserList")
    public List<UserInfo> index(){

        List<UserInfo> userInfos = userInfoMapper.getAll();
        return userInfos;
    }

    @RequestMapping("/getUserInfoById")
    public UserInfo getUserById(@RequestParam("userId") String userId){
        UserInfo userInfo = userInfoMapper.getgetUserInfoById(userId);
        return  userInfo;
    }

    @RequestMapping("/getUserInfoByAccount")
    public UserInfo getUserInfoByAccount(@RequestParam("userAccount") String userAccount){
        UserInfo userInfo = userInfoMapper.getgetUserInfoById(userAccount);
        return  userInfo;
    }
}
