package com.majy.scoremanager.domain;

import java.io.Serializable;

/**
 * Created by majingyuan on 2017/5/28.
 * 用户类
 */

public class UserInfo  implements Serializable {

    private String userId;
    private String userName;
    private String userPassword;
    private String userAccount;

    public UserInfo(){

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
