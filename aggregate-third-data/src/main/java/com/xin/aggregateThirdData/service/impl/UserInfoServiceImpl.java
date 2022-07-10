package com.xin.aggregateThirdData.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.aggregateThirdData.mapper.UserInfoMapper;
import com.xin.aggregateThirdData.pojo.entity.UserInfo;
import com.xin.aggregateThirdData.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public PageInfo<UserInfo> getUserList() {
        PageHelper.startPage(1,10);
        List<UserInfo> userInfos = userInfoMapper.getUserList();
        return new PageInfo<>(userInfos);
    }
}
