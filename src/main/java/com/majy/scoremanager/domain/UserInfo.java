package com.majy.scoremanager.domain;

import lombok.Data;

import java.io.Serializable;
import java.security.Timestamp;

/**
 * @author majingyuan
 * @date 2017/5/28.
 * 用户类
 */
@Data
public class UserInfo  implements Serializable {

    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     *用户账号
     */
    private String userAccount;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 旧密码，用于密码修改
     */
    private String userOldPassword;
    /**
     * 用户所属单位
     */
    private String userDepartment;
    /**
     * 用户token
     */
    private String userToken;
//    private Timestamp updateTime;
    /**
     * 激活标识，0不可用，1可用
     */
    private String activeFlag;
    /**
     * 用户角色，0管理员，1普通用户
     */
    private String userRole;

}
