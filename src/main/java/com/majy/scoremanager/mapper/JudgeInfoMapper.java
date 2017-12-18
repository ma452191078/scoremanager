package com.majy.scoremanager.mapper;

import com.majy.scoremanager.domain.JudgeInfo;

import java.util.List;

/**
 * Created by majingyuan on 2017/5/28.
 * 用户
 */

public interface JudgeInfoMapper {

    /**
     * 查询所有评委
     * @return
     */
    List<JudgeInfo> getAll();

    /**
     * 查询评委列表
     * @param judgeInfo
     * @return
     */
    List<JudgeInfo> getList(JudgeInfo judgeInfo);

    /**
     * 查询评委信息
     * @param judgeId
     * @return
     */
    JudgeInfo getJudgeInfoById(String judgeId);

    /**
     * 根据比赛ID查询评委列表
     * @param gameId
     * @return
     */
    List<JudgeInfo> getJudgeInfoByGameId(String gameId);

    /**
     * 插入评委信息
     * @param judgeInfo
     * @return
     */
    int insert(JudgeInfo judgeInfo);

    /**
     * 更新评委信息
     * @param judgeInfo
     * @return
     */
    int update(JudgeInfo judgeInfo);

    /**
     * 删除评委信息
     * @param judgeId
     * @return
     */
    int delete(String judgeId);
}
