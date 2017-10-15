package com.majy.scoremanager.controller;

import com.majy.scoremanager.constant.AppConstant;
import com.majy.scoremanager.domain.UserInfo;
import com.majy.scoremanager.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    public Map<String,Object> getUserList(){
        Map<String,Object> param = new HashMap<>();
        List<UserInfo> userList = userInfoMapper.getAll();
        param.put("userList", userList);
        return param;
    }

    @RequestMapping("/getUserInfoById")
    public UserInfo getUserById(@RequestParam("userId") String userId){
        UserInfo userInfo = userInfoMapper.getUserInfoById(userId);
        return  userInfo;
    }

    @RequestMapping("/getUserInfoByToken")
    public UserInfo getUserInfoByToken(@RequestParam("userToken") String userToken,
                                       @RequestParam("userId") String userId){
        UserInfo userInfo = userInfoMapper.getUserInfoByToken(userToken);
        if (userInfo != null && userId.equals(userInfo.getUserId())){
            return userInfo;
        }
        return null;
    }

    /**
     * 新增或修改用户信息
     * @param userInfo
     * @return
     */
    @RequestMapping("/updateUserInfo")
    public  Map<String, String> updateUserInfo(UserInfo userInfo){
        Map<String, String> resultMap = new HashMap<>();
        int dbFlag = AppConstant.DB_WRITE_FAILED;
        if (userInfo.getUserId() == null || "".equals(userInfo.getUserId())){
            userInfo.setUserId(UUID.randomUUID().toString());
            userInfo.setActiveFlag(AppConstant.ACTIVE_FLAG_ACTIVE);
            if (userInfoMapper.insert(userInfo) > 0) {
                dbFlag = AppConstant.DB_WRITE_SUCCESS;
            }
        }else {
            if (userInfoMapper.update(userInfo) > 0) {
                dbFlag = AppConstant.DB_WRITE_SUCCESS;
            }
        }

        resultMap.put("errFlag", String.valueOf(dbFlag));
        return  resultMap;
    }

    /**
     * 检查用户是否为管理员
     * @param userId
     * @return
     */
    @RequestMapping("/checkUserInfoById")
    public String checkUserInfoById(@RequestParam("userId") String userId){

        String isAdmin = "";
        UserInfo userInfo = userInfoMapper.getUserInfoById(userId);
        if (userInfo != null && "admin".equals(userInfo.getUserAccount())){
            isAdmin = "<li ><a href='#' @click='userManager({})'>用户管理</a></li>";
        }
        return  isAdmin;
    }
}
