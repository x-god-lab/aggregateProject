package com.xin.aggregateInfo.controller;


import com.xin.aggregateInfo.service.WeiboDataService;
import com.xin.utils.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 微博数据 前端控制器
 * </p>
 *
 * @author xin
 * @since 2022-02-20
 */
@RestController
@RequestMapping("/weiboData")
public class WeiboDataController {

    @Autowired
    private WeiboDataService weiboDataService;

    @ApiOperation("导入微博数据")
    @PostMapping("importExcel")
    public Response<String> importExcel(MultipartFile file,String author){
        try {
            InputStream in = file.getInputStream();
            weiboDataService.importExcel(in,author);
            return Response.success("导入成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.error("导入失败");
    }
}

