package com.xin.aggregateInfo.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author xin
 * @create 2022/03/06 14:16
 * @description
 **/
@Data
public class AreaOrgCodeVO {

    @ApiModelProperty("地区编码")
    private String orgCode;

    @ApiModelProperty("地区名称")
    private String orgName;

    @ApiModelProperty("父级编码")
    private String parentCode;

    @ApiModelProperty("排序")
    private Integer weight;

    @ApiModelProperty("子类")
    private List<AreaOrgCodeVO> children;
}
