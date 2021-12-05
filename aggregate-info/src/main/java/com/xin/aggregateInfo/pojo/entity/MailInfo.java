package com.xin.aggregateInfo.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 邮件信息
 * </p>
 *
 * @author xin
 * @since 2021-11-21
 */
public class MailInfo implements Serializable {


    /**
     * 邮件Id
     */
    @TableId(value = "mail_id", type = IdType.AUTO)
    private Integer mailId;

    /**
     * 发送人
     */
    private String sender;

    /**
     * 接收人
     */
    private String receiver;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String text;

    /**
     * 发送时间
     */
    @TableField("send_time")
    private LocalDateTime sendTime;

    /**
     * 抄送
     */
    private String cc;

    /**
     * 密送
     */
    private String gcc;

    /**
     * 状态
     */
    private String status;

    /**
     * 邮件附件
     */
    private String file;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;


    public Integer getMailId() {
        return mailId;
    }

    public MailInfo setMailId(Integer mailId) {
        this.mailId = mailId;
        return this;
    }

    public String getSender() {
        return sender;
    }

    public MailInfo setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public String getReceiver() {
        return receiver;
    }

    public MailInfo setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public MailInfo setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getText() {
        return text;
    }

    public MailInfo setText(String text) {
        this.text = text;
        return this;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public MailInfo setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
        return this;
    }

    public String getCc() {
        return cc;
    }

    public MailInfo setCc(String cc) {
        this.cc = cc;
        return this;
    }

    public String getGcc() {
        return gcc;
    }

    public MailInfo setGcc(String gcc) {
        this.gcc = gcc;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public MailInfo setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getFile() {
        return file;
    }

    public MailInfo setFile(String file) {
        this.file = file;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public MailInfo setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "MailInfo{" +
        "mailId=" + mailId +
        ", sender=" + sender +
        ", receiver=" + receiver +
        ", subject=" + subject +
        ", text=" + text +
        ", sendTime=" + sendTime +
        ", cc=" + cc +
        ", gcc=" + gcc +
        ", status=" + status +
        ", file=" + file +
        ", createTime=" + createTime +
        "}";
    }
}
