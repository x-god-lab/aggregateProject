package com.xin.aggregateInfo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description
 * @author:xin
 * @create:2023-09-23 17:30
 **/
@Data
public class BookSourceDTO {

    @ApiModelProperty("json地址")
    private String jsonAddress;
}
