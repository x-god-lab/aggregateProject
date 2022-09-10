package com.xin.aggregateThirdData.pojo.entity;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xin
 * @since 2022-03-22
 */
@Data
public class UserInfo {

    
    private String userId;
    
    private Integer id;
    
    private String name;
    
    private String idCardNum;
    
    private String cellPhone;

    /**
     * 家庭住址
     */
    private String address;

    /**
     * 性别
     */
    private String sex;

    /**
     * 出生日期
     */
    private Date birth;

    /**
     * 学历
     */
    private String degree;

    /**
     * 城市
     */
    private String city;

    /**
     * 地区
     */
    private String area;


    private String companyName;

    /**
     * 部门名
     */
    private String department;

    /**
     * 邮箱
     */
    private String email;


    private Date createTime;


    private Date updateTime;
    
}
