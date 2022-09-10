package com.xin.enumeration;

/**
 * @description
 * @author:xin
 * @create:2022-08-14 02:41
 **/
public enum MinioEnum {
    /**
     * minio公用
     */
    PUBLIC_OSS("publicoss");


    MinioEnum(String bucketName) {
        this.bucketName = bucketName;
    }

    private String bucketName;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
