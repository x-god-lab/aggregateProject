package com.xin.aggregateInfo.pojo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author xin
 * @create 2022/02/20 1:49
 * @description
 **/
@Data
@EqualsAndHashCode
public class WeiboDataDTO {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("微博id")
    @ExcelProperty(index = 0)
    private String weiboId;

    @ApiModelProperty("微博正文")
    @ExcelProperty("微博正文")
    private String content;

    @ApiModelProperty("头条文章url")
    @ExcelProperty("头条文章url")
    private String articleUrl;

    @ApiModelProperty("原始图片url")
    @ExcelProperty("原始图片url")
    private String originalPictures;

    @ApiModelProperty("微博视频url")
    @ExcelProperty("微博视频url")
    private String videoUrl;

    @ApiModelProperty("发布位置")
    @ExcelProperty("发布位置")
    private String publishPlace;

    @ApiModelProperty("发布时间")
    @ExcelProperty("发布时间")
    private String publishTime;

    @ApiModelProperty("发布工具")
    @ExcelProperty("发布工具")
    private String publishTool;

    @ApiModelProperty("点赞数")
    @ExcelProperty("点赞数")
    private String upNum;

    @ApiModelProperty("转发数")
    @ExcelProperty("转发数")
    private String retweetNum;

    @ApiModelProperty("评论数")
    @ExcelProperty("评论数")
    private String commentNum;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
