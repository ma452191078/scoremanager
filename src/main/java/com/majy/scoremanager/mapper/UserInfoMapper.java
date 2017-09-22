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

    UserInfo getUserInfoById(String userId);

    UserInfo getUserInfoByAccount(String userAccount);

    UserInfo getUserInfoByToken(String userToken);

    int insert(UserInfo user);

    int update(UserInfo user);

    int delete(String userId);
}
