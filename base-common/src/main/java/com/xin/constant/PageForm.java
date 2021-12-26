package com.xin.constant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xin
 * @create 2021/12/26 4:07
 * @description
 **/
@Data
@ApiModel("分页参数")
public class PageForm {

    @ApiModelProperty("页数")
    private Integer limit;

    @ApiModelProperty("列数")
    private Integer size;
}
