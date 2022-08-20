package com.xin.aggregateInfo.service;

import com.xin.aggregateInfo.pojo.upload.MinioDeleteParams;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xin
 */
public interface CommonService {
    /**
     * 使用Minio进行文件上传
     * @param file
     * @return
     */
    String uploadByMinio(MultipartFile file,String dbTable);

    /**
     * 使用Minio进行删除
     * @param params
     * @return
     */
    Boolean deleteByMinio(MinioDeleteParams params);
}
