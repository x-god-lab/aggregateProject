package com.info.aggregateinfo.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.info.aggregateinfo.mapper.MailInfoMapper;
import com.info.aggregateinfo.pojo.dto.MailDTO;
import com.info.aggregateinfo.pojo.entity.MailInfo;
import com.info.aggregateinfo.service.MailInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * 邮件信息 服务实现类
 * </p>
 *
 * @author xin
 * @since 2021-11-21
 */
@Service
public class MailInfoServiceImpl extends ServiceImpl<MailInfoMapper, MailInfo> implements MailInfoService {

    @Resource
    private JavaMailSenderImpl mailSender;

    @Override
    public String sendMail(MailDTO params) {
        try {
            sendMineMail(params);
            return saveMail(params);
        }catch (Exception e){
            throw new RuntimeException("邮件发送失败");
        }
    }

    private String saveMail(MailDTO params) {
        MailInfo mailInfo = new MailInfo();
        BeanUtils.copyProperties(params,mailInfo);
        mailInfo.setCreateTime(LocalDateTime.now());
        int insert = baseMapper.insert(mailInfo);
        if (insert > 0){
            return "保存成功";
        }else {
            throw new RuntimeException("保存失败");
        }

    }

    private void sendMineMail(MailDTO params){
        try {
            //true表示支持复杂类型
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
            //邮件发信人
            messageHelper.setFrom(params.getSender());
            //邮件收信人
            messageHelper.setTo(params.getReceiver().split(","));
            //邮件主题
            messageHelper.setSubject(params.getSubject());
            //邮件内容
            messageHelper.setText(params.getText());
            //抄送
            if (!StrUtil.isEmpty(params.getCc())) {
                messageHelper.setCc(params.getCc().split(","));
            }
            //密送
            if (!StrUtil.isEmpty(params.getBcc())) {
                messageHelper.setCc(params.getBcc().split(","));
            }
            //添加邮件附件
            if (params.getMultipartFiles() != null) {
                for (MultipartFile multipartFile : params.getMultipartFiles()) {
                    messageHelper.addAttachment(Objects.requireNonNull(multipartFile.getOriginalFilename()), multipartFile);
                }
            }
            //发送时间
            params.setSendTime(LocalDateTime.now());
            //正式发送邮件
            mailSender.send(messageHelper.getMimeMessage());
            params.setStatus("ok");
        } catch (Exception e) {
            //发送失败
            throw new RuntimeException(e);
        }
    }

}
