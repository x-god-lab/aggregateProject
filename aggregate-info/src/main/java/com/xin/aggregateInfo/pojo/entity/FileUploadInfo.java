package com.xin.aggregateInfo.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文件上传
 * </p>
 *
 * @author xin
 * @since 2022-08-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("file_upload_info")
public class FileUploadInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件Id
     */
    @TableId(value = "file_id", type = IdType.ASSIGN_ID)
    private String fileId;

    /**
     * 文件名
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 文件存放地址
     */
    @TableField("file_path")
    private String filePath;

    /**
     * 文件类型
     */
    @TableField("file_type")
    private String fileType;

    /**
     * 关联表名
     */
    @TableField("associate_table")
    private String associateTable;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


}
