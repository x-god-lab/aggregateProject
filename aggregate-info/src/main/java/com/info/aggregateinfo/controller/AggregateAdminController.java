package com.info.aggregateinfo.controller;

import com.info.aggregateinfo.pojo.dto.AggregateAdminDTO;
import com.info.aggregateinfo.pojo.entity.AggregateAdmin;
import com.info.aggregateinfo.service.AggregateAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.Response;

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
    public Response<AggregateAdmin> register(@RequestBody AggregateAdminDTO params){
        AggregateAdmin aggregateAdmin = aggregateAdminService.register(params);
        return Response.success("成功",aggregateAdmin);
    }
}

