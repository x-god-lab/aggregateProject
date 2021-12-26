package com.xin.aggregateInfo.pojo.dto;

import com.xin.constant.PageForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xin
 * @create 2021/12/26 3:07
 * @description
 **/
@Data
public class JokeCollectionDTO extends PageForm {

    @ApiModelProperty("内容")
    private String content;
}
