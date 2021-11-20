package com.info.aggregateinfo.controller;


import com.info.aggregateinfo.pojo.dto.MailDTO;
import com.info.aggregateinfo.service.MailInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import utils.Response;

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
public class MailInfoController {

    @Autowired
    private MailInfoService mailInfoService;

    @PostMapping("mailSend")
    public Response<String> mailSend(@RequestBody @Validated MailDTO params){
        String response = mailInfoService.sendMail(params);
        return Response.success(response);
    }
}

