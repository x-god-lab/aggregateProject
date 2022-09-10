package com.xin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author xin
 */
@Data
public class AggregateAdminVO {

    private String id;

    private String username;

    private String password;

    private String salt;

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
    private String nickName;

    /**
     * 备注信息
     */
    private String note;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后登录时间
     */
    private Date loginTime;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    private Integer status;

    private String roleId;

    private List<String> menuIdList;

    private List<String> permissionList;


    @Data
    public static class RoleVO{

        @ApiModelProperty("拥有角色Id")
        private String menuId;

        @ApiModelProperty("拥有权限")
        private String perm;

    }
}

