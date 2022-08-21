package com.xin.aggregateInfo.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.xin.aggregateInfo.mapper.FileUploadInfoMapper;
import com.xin.aggregateInfo.pojo.entity.FileUploadInfo;
import com.xin.aggregateInfo.pojo.upload.MinioDeleteParams;
import com.xin.aggregateInfo.service.CommonService;
import com.xin.aggregateInfo.util.MinioUtil;
import com.xin.enumeration.MinioEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author xin
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private FileUploadInfoMapper fileUploadInfoMapper;

    @Override
    public String uploadByMinio(MultipartFile file,String dbTable){
        FileUploadInfo fileUploadInfo = new FileUploadInfo();
        getFileUploadInfo(fileUploadInfo,file,dbTable);
        fileUploadInfoMapper.insert(fileUploadInfo);
        return fileUploadInfo.getFilePath();
    }
    @Override
    public Boolean deleteByMinio(MinioDeleteParams params) {
        MinioUtil.deleteFile(params.getBucketName(),params.getFilePath());
        return true;
    }

    @Override
    public String previewPhoto(MinioDeleteParams params) {
        return MinioUtil.preview(params.getFilePath(),params.getBucketName());
    }

    @Override
    public List<String> uploadBatchByMinio(List<MultipartFile> files, String dbTable) {
        List<String> list = new ArrayList<>();
        for (MultipartFile file : files) {
            FileUploadInfo fileUploadInfo = new FileUploadInfo();
            getFileUploadInfo(fileUploadInfo,file,dbTable);
            list.add(fileUploadInfo.getFilePath());
            fileUploadInfoMapper.insert(fileUploadInfo);
        }
        return list;

    }

    private void getFileUploadInfo(FileUploadInfo fileUploadInfo,MultipartFile file,String dbTable){
        fileUploadInfo.setFileId(IdUtil.fastSimpleUUID());
        fileUploadInfo.setFileType(Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".")+1));
        fileUploadInfo.setCreateTime(DateUtil.date());
        fileUploadInfo.setAssociateTable(dbTable);
        String uploadFile = MinioUtil.uploadFile(file, MinioEnum.PUBLIC_OSS.getBucketName());
        fileUploadInfo.setFilePath(uploadFile);
        fileUploadInfo.setFileName(StrUtil.subBetween(uploadFile,Objects.requireNonNull(uploadFile).substring(0,uploadFile.lastIndexOf("/")+1),uploadFile.substring(uploadFile.lastIndexOf("."))));
    }
}
