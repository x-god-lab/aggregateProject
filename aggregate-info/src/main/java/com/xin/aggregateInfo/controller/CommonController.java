package com.xin.aggregateInfo.controller;

import com.xin.aggregateInfo.pojo.upload.MinioDeleteParams;
import com.xin.aggregateInfo.service.CommonService;
import com.xin.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xin
 */
@RestController
@RequestMapping("common")
@Api(tags = "通用服务")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @PostMapping("updateByMinio")
    @ApiOperation("使用minio进行上传")
    public Response<String> updateByMinio(MultipartFile file) {
        return Response.success(commonService.updateByMinio(file));
    }

    @PostMapping("deleteByMinio")
    @ApiOperation("使用minio删除")
    public Response<Boolean> deleteByMinio(@RequestBody MinioDeleteParams params){
        return Response.success(commonService.deleteByMinio(params));
    }
}
