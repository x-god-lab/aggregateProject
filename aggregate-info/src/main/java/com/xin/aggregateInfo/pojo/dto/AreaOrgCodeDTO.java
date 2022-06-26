package com.xin.aggregateInfo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author xin
 * @create 2022/03/06 4:06
 * @description
 **/
@Data
public class AreaOrgCodeDTO {

    @ApiModelProperty("地区编码")
    private String code;

    @ApiModelProperty("地区名称")
    private String name;

    @ApiModelProperty("子类")
    private List<AreaOrgCodeDTO> children;
}
