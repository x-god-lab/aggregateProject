package com.xin.aggregateInfo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xin
 * @create 2022/01/15 15:20
 * @description
 **/
@Data
public class SaveMusicDTO {

    @ApiModelProperty("下载歌曲数量")
    private Integer num;

    @ApiModelProperty("榜单")
    private String value;
}
