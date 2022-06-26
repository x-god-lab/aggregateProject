package com.xin.aggregateInfo.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xin
 * @since 2022-01-14
 */
public class MusicInformation implements Serializable {


    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 音乐名字
     */
    @TableField("music_name")
    private String musicName;

    /**
     * 音乐地址
     */
    @TableField("music_url")
    private String musicUrl;

    /**
     * 音乐海报
     */
    @TableField("music_picurl")
    private String musicPicurl;

    /**
     * 作者
     */
    private String author;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


    public Integer getId() {
        return id;
    }

    public MusicInformation setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMusicName() {
        return musicName;
    }

    public MusicInformation setMusicName(String musicName) {
        this.musicName = musicName;
        return this;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public MusicInformation setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
        return this;
    }

    public String getMusicPicurl() {
        return musicPicurl;
    }

    public MusicInformation setMusicPicurl(String musicPicurl) {
        this.musicPicurl = musicPicurl;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public MusicInformation setAuthor(String author) {
        this.author = author;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public MusicInformation setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public MusicInformation setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "MusicInformation{" +
        "id=" + id +
        ", musicName=" + musicName +
        ", musicUrl=" + musicUrl +
        ", musicPicurl=" + musicPicurl +
        ", author=" + author +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
