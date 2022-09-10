package com.xin.aggregatesearch.pojo.dto;

import com.xin.constant.PageForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xin
 * @create 2022/03/23 1:04
 * @description
 **/
@Data
public class UserInfoDTO extends PageForm {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("身份证号")
    private String idCardNum;

    @ApiModelProperty("手机号")
    private String cellPhone;
}
