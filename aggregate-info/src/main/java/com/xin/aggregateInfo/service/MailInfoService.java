package com.xin.aggregateInfo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.aggregateInfo.pojo.dto.MailDTO;
import com.xin.aggregateInfo.pojo.entity.MailInfo;

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
