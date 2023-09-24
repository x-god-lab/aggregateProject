package com.xin.aggregateInfo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description
 * @author:xin
 * @create:2023-09-23 17:05
 **/
@Data
public class GeneratorTableDTO {

    @ApiModelProperty("表名")
    private String tableName;
}
