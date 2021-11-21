package com.info.aggregateinfo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.info.aggregateinfo.pojo.dto.AggregateAdminDTO;
import com.info.aggregateinfo.pojo.dto.LoginDTO;
import com.info.aggregateinfo.pojo.entity.AggregateAdmin;
import utils.Response;

/**
 * <p>
 * 用户信息表	 服务类
 * </p>
 *
 * @author xin
 * @since 2021-11-14
 */
public interface AggregateAdminService extends IService<AggregateAdmin> {

    /**
    * @author xin
    * @create 2021/11/17 2:35
    * @description 用户注册信息
    **/
    AggregateAdmin register(AggregateAdminDTO params);

    /**
    * @author xin
    * @create 2021/11/21 14:58
    * @description 登录
    **/
    Response<Object> login(LoginDTO params);
}
