package com.xin.aggregateInfo.pojo.upload;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xin
 */
@Data
@ConfigurationProperties(prefix = "minio")
@Component
public class MinioConfigParams {

    @ApiModelProperty("链接地址")
    private String url;

    @ApiModelProperty("用户名")
    private String accessKey;

    @ApiModelProperty("密码")
    private String secretKey;
}
