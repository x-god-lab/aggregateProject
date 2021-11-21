package com.info.aggregateinfo.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author xin
 * @create 2021/11/21 14:54
 * @description
 **/
@Data
@ApiModel("登录参数")
public class LoginDTO {

    @ApiModelProperty(value = "用户名",required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码",required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "验证码",required = true)
    @NotEmpty(message = "验证码不能为空")
    private String code;
}
