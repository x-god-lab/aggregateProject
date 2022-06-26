package com.xin.aggregateInfo.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

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
@Data
public class AreaOrgCode implements Serializable {


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

    @TableField("weight")
    private Integer weight;

    @TableField("status")
    private Integer status;

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
}
