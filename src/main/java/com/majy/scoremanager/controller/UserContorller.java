package com.majy.scoremanager.controller;

import com.majy.scoremanager.constant.AppConstant;
import com.majy.scoremanager.domain.UserInfo;
import com.majy.scoremanager.mapper.UserInfoMapper;
import com.majy.scoremanager.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.security.provider.MD5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @Author majingyuan
 * @Date  2017/5/28.
 *
 */
@RestController
@RequestMapping("/user")
public class UserContorller {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 获取用户列表
     * @return
     */
    @RequestMapping("/getUserList")
    public Map<String,Object> getUserList(){
        Map<String,Object> param = new HashMap<>();
        List<UserInfo> userList = userInfoMapper.getAll();
        param.put("userList", userList);
        return param;
    }

    /**
     * 根据userid获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping("/getUserInfoById")
    public UserInfo getUserById(@RequestParam("userId") String userId){
        UserInfo userInfo = userInfoMapper.getUserInfoById(userId);
        return  userInfo;
    }

    /**
     * 根据token获取用户信息
     * @param userToken
     * @param userId
     * @return
     */
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
        String resultMsg = "用户信息编辑失败";

        if (userInfo != null){
            if (userInfo.getUserId() == null || "".equals(userInfo.getUserId())){
                userInfo.setUserPassword(MD5Util.encode(userInfo.getUserPassword()));
                userInfo.setUserId(UUID.randomUUID().toString());
                userInfo.setActiveFlag(AppConstant.ACTIVE_FLAG_ACTIVE);
                if (userInfoMapper.insert(userInfo) > 0) {
                    dbFlag = AppConstant.DB_WRITE_SUCCESS;
                    resultMsg = "用户创建成功";
                }
            }else {

                if (userInfoMapper.update(userInfo) > 0) {
                    dbFlag = AppConstant.DB_WRITE_SUCCESS;
                    resultMsg = "用户修改成功";
                }
            }
        }
        resultMap.put("errFlag", String.valueOf(dbFlag));
        resultMap.put("errMsg", String.valueOf(resultMsg));
        return  resultMap;
    }

    /**
     * 修改密码
     * @param userInfo
     * @return
     */
    @RequestMapping("/changePassword")
    public Map<String, String> changePassword(UserInfo userInfo){

        Map<String, String> resultMap = new HashMap<>();
        int dbFlag = AppConstant.DB_WRITE_FAILED;
        String resultMsg = "";

        if (userInfo != null && !"".equals(userInfo.getUserId())){
            UserInfo changeUser = userInfoMapper.getUserInfoById(userInfo.getUserId());
            if (changeUser != null){
                if (changeUser.getUserPassword().equals(MD5Util.encode(userInfo.getUserPassword()))){
                    changeUser.setUserPassword(MD5Util.encode(userInfo.getUserPassword()));
                    if (userInfoMapper.changeUserPassword(changeUser) > 0){
                        dbFlag = AppConstant.DB_WRITE_SUCCESS;
                        resultMsg = "密码修改成功";
                    } else {
                        resultMsg = "密码修改失败";
                    }
                } else {
                    // original password error
                    resultMsg = "原密码错误";
                }
            } else {
                //user is not exist
                resultMsg = "用户不存在";
            }
        }

        resultMap.put("errFlag", String.valueOf(dbFlag));
        resultMap.put("errMsg", resultMsg);
        return resultMap;
    }



    /**
     * 检查用户是否为管理员
     * @param userId 用户id
     * @return 用户管理菜单
     */
    @RequestMapping("/checkUserInfoById")
    public Map<String,String> checkUserInfoById(@RequestParam("userId") String userId){

        Map<String,String> result = new HashMap<>();
        String isAdmin = "";
        UserInfo userInfo = userInfoMapper.getUserInfoById(userId);
        if (userInfo != null && "admin".equals(userInfo.getUserAccount())){
            isAdmin = "<li ><a href='#' onclick='userManager();'>用户管理</a></li>";
        }
        result.put("menu", isAdmin);
        return  result;
    }

    /**
     * 激活或禁用用户
     * @param userId    用户id
     * @param activeFlag    激活/禁用标识
     * @return
     */
    @RequestMapping("/activeUser")
    public Map<String, String> activeUser(@RequestParam("userId") String userId, @RequestParam("activeFlag") String activeFlag){
        Map<String,String> resultMap = new HashMap<>();
        int dbFlag = AppConstant.DB_WRITE_FAILED;
        if (!"".equals(userId)){
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setActiveFlag(activeFlag);
            if (userInfoMapper.update(userInfo) > 0){
                dbFlag = AppConstant.DB_WRITE_SUCCESS;
            }
        }
        resultMap.put("errFlag", String.valueOf(dbFlag));

        return resultMap;
    }

    /**
     * 重置密码
     * @param userId
     * @return
     */
    @RequestMapping("/resetUserPassword")
    public Map<String, String> resetUserPassword(@RequestParam("userId") String userId){
        Map<String,String> resultMap = new HashMap<>();
        String errMsg = AppConstant.REQUEST_ERROR_VALUE;
        if (!"".equals(userId)){
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setUserPassword(MD5Util.encode(AppConstant.DEFAULT_PASSWORD));
            if (userInfoMapper.update(userInfo) > 0){
                errMsg = AppConstant.REQUEST_SUCCESS_VALUE;
            }
        }
        resultMap.put("errMsg", String.valueOf(errMsg));

        return resultMap;
    }
}
