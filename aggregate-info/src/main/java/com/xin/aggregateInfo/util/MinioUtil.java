package com.xin.aggregateInfo.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.log.StaticLog;
import com.xin.aggregateInfo.pojo.upload.MinioConfigParams;
import io.minio.*;
import io.minio.http.Method;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
            String filePath = getFileName() + IdUtil.fastSimpleUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(filePath)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType()).build());
            return filePath;
        }catch (Exception e){
            StaticLog.error(e);
        }
        return null;
    }

    /**
     * 预览图片
     * @param fileName
     * @return
     */
    @SneakyThrows
    public static String preview(String fileName,String bucketName){
        Map<String, String> reqParams = new HashMap<String, String>(1);
        reqParams.put("default","application/octet-stream");
        reqParams.put("jpg", "image/jpeg");
        reqParams.put("tiff", "image/tiff");
        reqParams.put("gif", "image/gif");
        reqParams.put("jfif", "image/jpeg");
        reqParams.put("png", "image/png");
        reqParams.put("tif", "image/tiff");
        reqParams.put("ico", "image/x-icon");
        reqParams.put("jpeg", "image/jpeg");
        reqParams.put("wbmp", "image/vnd.wap.wbmp");
        reqParams.put("fax", "image/fax");
        reqParams.put("net", "image/pnetvue");
        reqParams.put("jpe", "image/jpeg");
        reqParams.put("rp", "image/vnd.rn-realpix");
        reqParams.put("mp4", "video/mp4");
        // 查看文件地址
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(fileName)
                        .expiry(2, TimeUnit.HOURS)
                        .extraQueryParams(reqParams)
                        .build());
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
