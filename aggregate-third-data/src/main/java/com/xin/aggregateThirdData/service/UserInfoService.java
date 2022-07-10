package com.xin.aggregateThirdData.service;

import com.github.pagehelper.PageInfo;
import com.xin.aggregateThirdData.pojo.entity.UserInfo;

/**
 * @author xin
 */
public interface UserInfoService {
    /**
     * @ author xin
     * @ return UserInfo
     * @
     */
    PageInfo<UserInfo> getUserList();
}
