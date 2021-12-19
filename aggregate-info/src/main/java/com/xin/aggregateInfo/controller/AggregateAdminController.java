package com.xin.aggregateInfo.controller;

import com.xin.aggregateInfo.pojo.dto.AggregateAdminDTO;
import com.xin.aggregateInfo.pojo.dto.LoginDTO;
import com.xin.aggregateInfo.service.AggregateAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.xin.utils.Response;

/**
 * <p>
 * 用户信息表	 前端控制器
 * </p>
 *
 * @author xin
 * @since 2021-11-14
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "用户信息控制类")
public class AggregateAdminController {

    @Autowired
    private AggregateAdminService aggregateAdminService;

    @ApiOperation("注册")
    @PostMapping("register")
    public Response<Object> register(@RequestBody AggregateAdminDTO params){
        return aggregateAdminService.register(params);
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public Response<Object> login(@RequestBody @Validated LoginDTO params){
        return aggregateAdminService.login(params);
    }
}

