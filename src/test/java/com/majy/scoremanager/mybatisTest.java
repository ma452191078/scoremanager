package com.majy.scoremanager;

import com.majy.scoremanager.domain.UserInfo;
import com.majy.scoremanager.mapper.UserInfoMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by majingyuan on 2017/5/28.
 */
public class mybatisTest {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Test
    public void test() {
        List<UserInfo> userInfos = userInfoMapper.getAll();
        System.out.print(userInfos.toString());
    }
}
