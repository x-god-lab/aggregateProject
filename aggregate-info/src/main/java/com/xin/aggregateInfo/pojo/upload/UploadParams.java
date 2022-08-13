package com.xin.aggregateInfo.pojo.upload;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xin
 * @create 2022/01/16 17:09
 * @description
 **/
@Data
@ConfigurationProperties(prefix = "ftp")
@Component
public class UploadParams {

    @ApiModelProperty("服务器ip或者主机名")
    private String host;

    @ApiModelProperty("ftp端口")
    private int port;

    @ApiModelProperty("ftp使用的用户")
    private String username;

    @ApiModelProperty("账户密码")
    private String password;

    @ApiModelProperty("上传地址")
    private String filePath;

}
