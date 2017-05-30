package com.majy.scoremanager.mapper;

import com.majy.scoremanager.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by majingyuan on 2017/5/28.
 * 用户
 */

public interface UserInfoMapper {

    List<UserInfo> getAll();

    UserInfo getgetUserInfoById(String userId);

    UserInfo getUserInfoByAccount(String userAccount);

    void insert(UserInfo user);

    void update(UserInfo user);

    void delete(String userId);
}
