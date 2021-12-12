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
 * @since 2021-12-11
 */
public class JokeCollection implements Serializable {


    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 笑话内容
     */
    private String content;

    /**
     * 创建人
     */
    @TableField("create_name")
    private String createName;

    /**
     * 修改人
     */
    @TableField("update_name")
    private String updateName;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


    public Integer getId() {
        return id;
    }

    public JokeCollection setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public JokeCollection setContent(String content) {
        this.content = content;
        return this;
    }

    public String getCreateName() {
        return createName;
    }

    public JokeCollection setCreateName(String createName) {
        this.createName = createName;
        return this;
    }

    public String getUpdateName() {
        return updateName;
    }

    public JokeCollection setUpdateName(String updateName) {
        this.updateName = updateName;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public JokeCollection setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public JokeCollection setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "JokeCollection{" +
        "id=" + id +
        ", content=" + content +
        ", createName=" + createName +
        ", updateName=" + updateName +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
