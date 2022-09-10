package com.xin.aggregateInfo.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author xin
 * @create 2021/11/21 1:03
 * @description
 **/
@Data
public class MailDTO {

    @ApiModelProperty("发送人")
    private String sender;

    @ApiModelProperty("接收人")
    @NotBlank(message = "接收人不能为空")
    private String receiver;

    @ApiModelProperty("邮件主题")
    @NotBlank(message = "邮件主题不能为空")
    private String subject;

    @ApiModelProperty("邮件内容")
    @NotBlank(message = "邮件内容不能为空")
    private String text;

    @ApiModelProperty("发送时间")
    private LocalDateTime sendTime;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("抄送")
    private String cc;

    @ApiModelProperty("密送")
    private String bcc;

    @ApiModelProperty("邮件附件")
    @JsonIgnore
    private MultipartFile[] multipartFiles;
}
