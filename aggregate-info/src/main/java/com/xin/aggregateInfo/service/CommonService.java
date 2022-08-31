package com.xin.aggregateInfo.service;

import com.xin.aggregateInfo.pojo.upload.MinioDeleteParams;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
     * 预览照片
     * @param params
     * @return
     */
    String previewPhoto(MinioDeleteParams params);

    /**
     * minio进行批量上传
     * @param files
     * @param dbTable
     * @return
     */
    List<String> uploadBatchByMinio(List<MultipartFile> files, String dbTable);

    /**
     * pdf转word文件
     * @param file
     */
    void pdfToWord(MultipartFile file,String path);
}
