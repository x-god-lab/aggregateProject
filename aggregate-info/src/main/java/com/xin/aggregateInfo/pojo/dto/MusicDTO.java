package com.xin.aggregateInfo.pojo.dto;

import com.xin.constant.PageForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xin
 * @create 2022/01/14 1:10
 * @description
 **/
@Data
@ApiModel("音乐列表请求参数")
public class MusicDTO extends PageForm {

    @ApiModelProperty("姓名")
    private String author;

    @ApiModelProperty("音乐名称")
    private String musicName;

    @ApiModelProperty("开始时间")
    private String startTime;

    @ApiModelProperty("结束时间")
    private String endTime;
}
