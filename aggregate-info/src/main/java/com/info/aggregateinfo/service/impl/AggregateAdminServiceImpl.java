package com.info.aggregateinfo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.info.aggregateinfo.mapper.AggregateAdminMapper;
import com.info.aggregateinfo.pojo.dto.AggregateAdminDTO;
import com.info.aggregateinfo.pojo.dto.LoginDTO;
import com.info.aggregateinfo.pojo.entity.AggregateAdmin;
import com.info.aggregateinfo.service.AggregateAdminService;
import constant.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import utils.Response;
import utils.UUIDUtils;

import javax.annotation.Resource;
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

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public AggregateAdmin register(AggregateAdminDTO params) {
        QueryWrapper<AggregateAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", params.getUsername());
        AggregateAdmin admin = baseMapper.selectOne(queryWrapper);
        if (ObjectUtil.isNotEmpty(admin)){
            throw new RuntimeException("用户名重复，请重新输入！");
        }
        AggregateAdmin aggregateAdmin = new AggregateAdmin();
        BeanUtils.copyProperties(params,aggregateAdmin);
        aggregateAdmin.setId(UUIDUtils.getUUId());
        aggregateAdmin.setCreateTime(LocalDateTime.now());
        aggregateAdmin.setStatus(Constants.STATUS);
        aggregateAdmin.setPassword(SecureUtil.md5(params.getPassword()));
        baseMapper.insert(aggregateAdmin);
        return aggregateAdmin;
    }

    @Override
    public Response<Object> login(LoginDTO params){
        // 获取验证码
        String code = (String) redisTemplate.opsForValue().get("generate:ver:code");
        if (!params.getCode().equals(code)){
            return Response.error("验证码不正确，请重新登录");
        }
        QueryWrapper<AggregateAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", params.getUsername());
        AggregateAdmin admin = baseMapper.selectOne(queryWrapper);
        if (ObjectUtil.isEmpty(admin)){
            return Response.error("没有该用户的信息，请确认用户名是否正确");
        }
        if (!SecureUtil.md5(params.getPassword()).equals(admin.getPassword())){
            return Response.error("密码错误，请重新输入！");
        }
        return Response.success(admin);
    }
}
