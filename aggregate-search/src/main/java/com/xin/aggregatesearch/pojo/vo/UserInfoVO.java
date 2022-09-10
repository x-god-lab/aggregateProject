package com.xin.aggregatesearch.pojo.vo;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xin
 * @since 2022-03-22
 */
@Data
public class UserInfoVO {

    @ApiModelProperty("用户Id")
    private String userId;

    @ApiModelProperty("主键Id")
    private Integer id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("身份证号")
    private String idCardNum;

    @ApiModelProperty("手机号")
    private String cellPhone;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("出生日期")
    @JsonFormat(pattern = DatePattern.NORM_DATE_PATTERN)
    private Date birth;

    @ApiModelProperty("学历")
    private String degree;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("地区")
    private String area;

    @ApiModelProperty("公司名")
    private String companyName;

    @ApiModelProperty("部门名")
    private String department;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private Date updateTime;
}
