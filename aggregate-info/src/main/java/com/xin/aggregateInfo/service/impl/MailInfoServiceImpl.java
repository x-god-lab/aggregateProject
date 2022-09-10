package com.xin.aggregateInfo.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.aggregateInfo.constant.Constants;
import com.xin.aggregateInfo.mappers.master.MailInfoMapper;
import com.xin.aggregateInfo.pojo.dto.MailDTO;
import com.xin.aggregateInfo.pojo.entity.MailInfo;
import com.xin.aggregateInfo.service.MailInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendMail(MailDTO params) {
        try {
            sendMineMail(params);
            return saveMail(params);
        }catch (Exception e){
            throw new RuntimeException("邮件发送失败");
        }
    }

    @Override
    public void generateVerCode(String receiver) {
        // 生成随机验证码
        String code = randomCode();
        redisTemplate.opsForValue().set("generate:register:code",code,5, TimeUnit.MINUTES);
        MailDTO params = new MailDTO();
        params.setSender(sender);
        params.setReceiver(receiver);
        params.setSubject(Constants.YZM);
        params.setText("您好，你的验证码是："+code+"，五分钟后失效！");
        params.setSendTime(LocalDateTime.now());
        sendMineMail(params);
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

    private String randomCode(){
        Random random = new Random();
        random.nextInt();
        char[] ch = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k','l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        StringBuilder str = new StringBuilder();
        for (int i = 0;i < 6;i++){
            char num = ch[random.nextInt(ch.length)];
            str.append(num);
        }
        return str.toString();
    }

}
