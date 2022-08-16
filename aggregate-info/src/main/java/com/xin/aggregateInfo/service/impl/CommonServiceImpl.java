package com.xin.aggregateInfo.service.impl;

import com.xin.aggregateInfo.pojo.upload.MinioDeleteParams;
import com.xin.aggregateInfo.service.CommonService;
import com.xin.aggregateInfo.util.MinioUtil;
import com.xin.enumeration.MinioEnum;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xin
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Override
    public String uploadByMinio(MultipartFile file){
        return MinioUtil.uploadFile(file, MinioEnum.PUBLIC_OSS.getBucketName());
    }

    @Override
    public Boolean deleteByMinio(MinioDeleteParams params) {
        MinioUtil.deleteFile(params.getBucketName(),params.getFilePath());
        return true;
    }
}
