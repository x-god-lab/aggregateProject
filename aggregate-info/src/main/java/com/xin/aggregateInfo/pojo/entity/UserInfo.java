package com.xin.aggregateInfo.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xin
 * @since 2022-09-04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户Id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 身份证号
     */
    @TableField("id_card_num")
    private String idCardNum;

    /**
     * 手机号码
     */
    @TableField("cell_phone")
    private String cellPhone;

    /**
     * 家庭住址
     */
    @TableField("address")
    private String address;

    /**
     * 性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 出生日期
     */
    @TableField("birth")
    private LocalDate birth;

    /**
     * 学历
     */
    @TableField("degree")
    private String degree;

    /**
     * 城市
     */
    @TableField("city")
    private String city;

    /**
     * 地区
     */
    @TableField("area")
    private String area;

    /**
     * 公司名
     */
    @TableField("company_name")
    private String companyName;

    /**
     * 部门名
     */
    @TableField("department")
    private String department;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

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
