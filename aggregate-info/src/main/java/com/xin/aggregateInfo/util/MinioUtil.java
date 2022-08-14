package com.xin.aggregateInfo.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.log.StaticLog;
import com.xin.aggregateInfo.pojo.upload.MinioConfigParams;
import io.minio.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;

/**
 * @description
 * @author:xin
 * @create:2022-08-14 03:17
 **/
@Component
public class MinioUtil {

    public static MinioClient minioClient;

    @Autowired
    private MinioConfigParams minioConfigParams;

    @PostConstruct
    public void init(){
        try {
            StaticLog.info("minio正在连接...");
            minioClient = MinioClient.builder().endpoint(minioConfigParams.getUrl()).credentials(minioConfigParams.getAccessKey(), minioConfigParams.getSecretKey()).build();
            StaticLog.info("连接成功");
        }catch (Exception e){
            StaticLog.error(e);
        }
    }

    @SneakyThrows(Exception.class)
    public static boolean isExistBucket(String bucketName){
        BucketExistsArgs build = BucketExistsArgs.builder().bucket(bucketName).build();
        return minioClient.bucketExists(build);
    }

    /**
     * 上传文件
     */
    @SneakyThrows(Exception.class)
    public static String uploadFile(MultipartFile file, String bucketName){
        try {
            // 存储桶不存在即创建
            if (!isExistBucket(bucketName)){
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            String filePath = getFileName() + IdUtil.fastSimpleUUID() + originalFilename.substring(originalFilename.indexOf("."));
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(filePath)
                    .stream(file.getInputStream(), file.getSize(), -1).build());
            return filePath;
        }catch (Exception e){
            StaticLog.error(e);
        }
        return null;
    }


    /**
     * 文件删除
     */
    @SneakyThrows(Exception.class)
    public static void deleteFile(String bucketName,String filePath){
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(filePath)
                .build());
    }

    private static String getFileName(){
        DateTime date = DateUtil.date();
        return DateUtil.format(date, "yyyy/MM/dd/");
    }
}
