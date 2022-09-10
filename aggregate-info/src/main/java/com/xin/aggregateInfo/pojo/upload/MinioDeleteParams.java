package com.xin.aggregateInfo.pojo.upload;

import com.xin.enumeration.MinioEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description
 * @author:xin
 * @create:2022-08-15 05:21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MinioDeleteParams {

    @ApiModelProperty("文件地址")
    private String filePath;

    @ApiModelProperty("bucket名")
    private String bucketName = MinioEnum.PUBLIC_OSS.getBucketName();
}
