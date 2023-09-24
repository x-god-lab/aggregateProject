package com.xin.aggregateInfo.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author xin
 * @since 2023-09-23 17:36:12
 */
@Getter
@Setter
@TableName("book_source")
@ApiModel(value = "BookSource对象", description = "")
public class BookSource {

    @TableId("book_source_comment")
    private String bookSourceComment;

    @TableField("book_source_group")
    private String bookSourceGroup;

    @TableField("book_source_name")
    private String bookSourceName;

    @TableField("book_source_type")
    private String bookSourceType;

    @TableField("book_source_url")
    private String bookSourceUrl;

    @TableField("book_url_pattern")
    private String bookUrlPattern;

    @TableField("cover_decode_js")
    private String coverDecodeJs;

    @TableField("custom_order")
    private String customOrder;

    @TableField("enabled")
    private String enabled;

    @TableField("enabled_cookie_jar")
    private String enabledCookieJar;

    @TableField("enabled_explore")
    private String enabledExplore;

    @TableField("explore_url")
    private String exploreUrl;

    @TableField("header")
    private String header;

    @TableField("last_update_time")
    private String lastUpdateTime;

    @TableField("login_ui")
    private String loginUi;

    @TableField("login_url")
    private String loginUrl;

    @TableField("respond_time")
    private String respondTime;

    @TableField("rule_book_info")
    private String ruleBookInfo;

    @TableField("rule_content")
    private String ruleContent;

    @TableField("rule_explore")
    private String ruleExplore;
}
