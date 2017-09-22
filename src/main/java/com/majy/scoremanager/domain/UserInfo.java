package com.majy.scoremanager.domain;

import lombok.Data;

import java.io.Serializable;
import java.security.Timestamp;

/**
 * Created by majingyuan on 2017/5/28.
 * 用户类
 */
@Data
public class UserInfo  implements Serializable {

    private String userId;
    private String userName;
    private String userPassword;
    private String userDepartment;
    private String userToken;
    private Timestamp updateTime;
    private String activeFlag;

}
