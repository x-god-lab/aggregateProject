package com.xin.aggregateInfo.controller;


import com.xin.aggregateInfo.pojo.dto.MailDTO;
import com.xin.aggregateInfo.service.MailInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.xin.utils.Response;

/**
 * <p>
 * 邮件信息 前端控制器
 * </p>
 *
 * @author xin
 * @since 2021-11-21
 */
@RestController
@RequestMapping("/mailInfo")
@Api(tags = "邮件信息")
public class MailInfoController {

    @Autowired
    private MailInfoService mailInfoService;

    @ApiOperation("邮件发送")
    @PostMapping("mailSend")
    public Response<String> mailSend(@RequestBody @Validated MailDTO params){
        String response = mailInfoService.sendMail(params);
        return Response.success(response);
    }

    @ApiOperation(("发送验证码"))
    @PostMapping("generateVerCode")
    public Response<String> generateVerCode(@RequestBody MailDTO params){
        mailInfoService.generateVerCode(params.getReceiver());
        return Response.success("发送成功");
    }

}

