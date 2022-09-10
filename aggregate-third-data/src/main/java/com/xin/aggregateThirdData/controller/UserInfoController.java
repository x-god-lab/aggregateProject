package com.xin.aggregateThirdData.controller;

import com.github.pagehelper.PageInfo;
import com.xin.aggregateThirdData.pojo.entity.UserInfo;
import com.xin.aggregateThirdData.service.UserInfoService;
import com.xin.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xin
 */
@RequestMapping("user")
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("getUserList")
    public Response<PageInfo<UserInfo>> getUserList(){
        return Response.success(userInfoService.getUserList());
    }
}
