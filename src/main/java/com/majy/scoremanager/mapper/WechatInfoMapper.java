package com.majy.scoremanager.mapper;

import com.majy.scoremanager.domain.WechatInfo;


/**
 * Created by majingyuan on 2017/5/28.
 * 评分
 */

public interface WechatInfoMapper {

    WechatInfo getWechatInfo();

    int insert(WechatInfo wechatInfo);

    int update(WechatInfo wechatInfo);

}
