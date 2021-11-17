package com.info.aggregateinfo.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.info.aggregateinfo.mapper.AggregateAdminMapper;
import com.info.aggregateinfo.pojo.dto.AggregateAdminDTO;
import com.info.aggregateinfo.pojo.entity.AggregateAdmin;
import com.info.aggregateinfo.service.AggregateAdminService;
import constant.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import utils.UUIDUtils;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表	 服务实现类
 * </p>
 *
 * @author xin
 * @since 2021-11-14
 */
@Service
public class AggregateAdminServiceImpl extends ServiceImpl<AggregateAdminMapper, AggregateAdmin> implements AggregateAdminService {

    @Override
    public AggregateAdmin register(AggregateAdminDTO params) {
        AggregateAdmin aggregateAdmin = new AggregateAdmin();
        BeanUtils.copyProperties(params,aggregateAdmin);
        aggregateAdmin.setId(UUIDUtils.getUUId());
        aggregateAdmin.setCreateTime(LocalDateTime.now());
        aggregateAdmin.setStatus(Constants.STATUS);
        aggregateAdmin.setPassword(SecureUtil.md5(params.getPassword()));
        baseMapper.insert(aggregateAdmin);
        return aggregateAdmin;
    }
}
