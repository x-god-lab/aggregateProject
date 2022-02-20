package com.xin.aggregateInfo.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 微博数据
 * </p>
 *
 * @author xin
 * @since 2022-02-20
 */
@Data
public class WeiboData implements Serializable {


    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 微博id
     */
    @TableField("weibo_id")
    private String weiboId;

    /**
     * 微博正文
     */
    private String content;

    /**
     * 头条文章url
     */
    @TableField("article_url")
    private String articleUrl;

    /**
     * 原始图片url	
     */
    @TableField("original_pictures")
    private String originalPictures;

    /**
     * 转发图片
     */
    @TableField("retweet_pictures")
    private String retweetPictures;

    /**
     * 是否原始
     */
    private String original;

    /**
     * 微博视频url	
     */
    @TableField("video_url")
    private String videoUrl;

    /**
     * 发布位置	
     */
    @TableField("publish_place")
    private String publishPlace;

    /**
     * 发布时间	
     */
    @TableField("publish_time")
    private String publishTime;

    /**
     * 发布工具	
     */
    @TableField("publish_tool")
    private String publishTool;

    /**
     * 点赞数	
     */
    @TableField("up_num")
    private String upNum;

    /**
     * 转发数	
     */
    @TableField("retweet_num")
    private String retweetNum;

    /**
     * 评论数	
     */
    @TableField("comment_num")
    private String commentNum;

    @TableField("author")
    private String author;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
