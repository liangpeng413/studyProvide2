package com.liang.study.mode.dao;

import com.liang.study.mode.BdUserInfo;

public interface BdUserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BdUserInfo record);

    int insertSelective(BdUserInfo record);

    BdUserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BdUserInfo record);

    int updateByPrimaryKey(BdUserInfo record);
}