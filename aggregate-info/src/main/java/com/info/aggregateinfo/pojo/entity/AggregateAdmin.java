package com.info.aggregateinfo.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表	
 * </p>
 *
 * @author xin
 * @since 2021-11-14
 */
@ApiModel(value = "用户信息表")
public class AggregateAdmin implements Serializable {


    @TableId(value = "id")
    private String id;

    private String username;

    private String password;

    /**
     * 头像
     */
    private String icon;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 备注信息
     */
    private String note;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 最后登录时间
     */
    @TableField("login_time")
    private LocalDateTime loginTime;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    private Integer status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public AggregateAdmin setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AggregateAdmin setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public AggregateAdmin setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AggregateAdmin setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public AggregateAdmin setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getNote() {
        return note;
    }

    public AggregateAdmin setNote(String note) {
        this.note = note;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public AggregateAdmin setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public AggregateAdmin setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public AggregateAdmin setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "AggregateAdmin{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", icon=" + icon +
        ", email=" + email +
        ", nickName=" + nickName +
        ", note=" + note +
        ", createTime=" + createTime +
        ", loginTime=" + loginTime +
        ", status=" + status +
        "}";
    }
}
