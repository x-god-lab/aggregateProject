package com.info.aggregateinfo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.info.aggregateinfo.pojo.dto.MailDTO;
import com.info.aggregateinfo.pojo.entity.MailInfo;

/**
 * <p>
 * 邮件信息 服务类
 * </p>
 *
 * @author xin
 * @since 2021-11-21
 */
public interface MailInfoService extends IService<MailInfo> {

    /**
    * @author xin
    * @create 2021/11/21 1:49
    * @description 发送邮件
    **/
    String sendMail(MailDTO params);

    /**
    * @author xin
    * @create 2021/11/21 13:55
    * @description 发送验证码
    **/
    void generateVerCode(String receiver);
}
