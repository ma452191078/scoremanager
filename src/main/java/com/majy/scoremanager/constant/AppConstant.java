package com.majy.scoremanager.constant;

/**
 * 系统常量
 * Created by majingyuan on 2017/9/22.
 */
public interface AppConstant {

//    用户状态
    String ACTIVE_FLAG_ACTIVE = "1";
    String ACTIVE_FLAG_UNACTIVE = "0";

//    请求状态
    String REQUEST_SUCCESS = "1";
    String REQUEST_ERROR = "0";
    String REQUEST_SUCCESS_VALUE = "成功";
    String REQUEST_ERROR_VALUE = "失败";

//    数据库读写状态
    Integer DB_WRITE_SUCCESS = 1;
    Integer DB_WRITE_FAILED = 0;

    String DEFAULT_PASSWORD = "123456";
}
