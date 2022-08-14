package com.xin.aggregateInfo.pojo.upload;

import com.xin.enumeration.MinioEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description
 * @author:xin
 * @create:2022-08-15 05:21
 **/
@Data
public class MinioDeleteParams {

    @ApiModelProperty("文件地址")
    private String filePath;

    @ApiModelProperty("bucket名")
    private String bucketName = MinioEnum.PUBLIC_OSS.getBucketName();
}
