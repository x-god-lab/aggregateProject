package com.xin.aggregateInfo.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 地区编码	
 * </p>
 *
 * @author xin
 * @since 2022-03-06
 */
public class OrgCode implements Serializable {


    /**
     * 地区编码
     */
    @TableId(value = "org_code", type = IdType.ASSIGN_ID)
    private String orgCode;

    /**
     * 地区名称
     */
    @TableField("org_name")
    private String orgName;

    /**
     * 父编码
     */
    @TableField("parent_code")
    private String parentCode;

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


    public String getOrgCode() {
        return orgCode;
    }

    public OrgCode setOrgCode(String orgCode) {
        this.orgCode = orgCode;
        return this;
    }

    public String getOrgName() {
        return orgName;
    }

    public OrgCode setOrgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public String getParentCode() {
        return parentCode;
    }

    public OrgCode setParentCode(String parentCode) {
        this.parentCode = parentCode;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public OrgCode setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public OrgCode setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "OrgCode{" +
        "orgCode=" + orgCode +
        ", orgName=" + orgName +
        ", parentCode=" + parentCode +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
