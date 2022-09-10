package com.xin.aggregateInfo.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息表	
 * </p>
 *
 * @author xin
 * @since 2021-11-14
 */
@ApiModel(value = "用户信息表")
@Data
public class AggregateAdmin implements Serializable {


    @TableId(value = "id")
    private String id;

    private String username;

    private String password;

    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

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
    private Date createTime;

    /**
     * 最后登录时间
     */
    @TableField("login_time")
    private Date loginTime;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    @TableField("status")
    private Integer status;

    @TableField("role_id")
    private String roleId;
}
