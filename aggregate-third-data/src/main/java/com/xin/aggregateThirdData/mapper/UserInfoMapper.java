package com.xin.aggregateThirdData.mapper;

import com.xin.aggregateThirdData.pojo.entity.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper {
    List<UserInfo> getUserList();
}
