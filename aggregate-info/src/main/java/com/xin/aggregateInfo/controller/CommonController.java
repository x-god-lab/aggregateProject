package com.xin.aggregateInfo.controller;

import com.xin.aggregateInfo.pojo.upload.MinioDeleteParams;
import com.xin.aggregateInfo.service.CommonService;
import com.xin.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author xin
 */
@RestController
@RequestMapping("common")
@Api(tags = "通用服务")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @PostMapping("uploadByMinio")
    @ApiOperation("使用minio进行上传")
    public Response<String> uploadByMinio(MultipartFile file,String dbTable) {
        return Response.success(commonService.uploadByMinio(file,dbTable));
    }

    @PostMapping("uploadBatchByMinio")
    @ApiOperation("使用minio进行批量上传")
    public Response<List<String>> uploadBatchByMinio(List<MultipartFile> files, String dbTable) {
        return Response.success(commonService.uploadBatchByMinio(files,dbTable));
    }

    @PostMapping("deleteByMinio")
    @ApiOperation("使用minio删除")
    public Response<Boolean> deleteByMinio(@RequestBody MinioDeleteParams params){
        return Response.success(commonService.deleteByMinio(params));
    }

    @PostMapping("previewPhoto")
    @ApiOperation("预览照片")
    public Response<String> previewPhoto(@RequestBody MinioDeleteParams params){
        return Response.success(commonService.previewPhoto(params));
    }

    @PostMapping("pdfToWord")
    @ApiOperation("pdf转word")
    public Response<String> pdfToWord(MultipartFile file,String path){
        commonService.pdfToWord(file,path);
        return Response.success("转换成功");
    }
}