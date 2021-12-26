package com.xin.aggregateInfo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.aggregateInfo.mapper.AggregateAdminMapper;
import com.xin.aggregateInfo.pojo.dto.AggregateAdminDTO;
import com.xin.aggregateInfo.pojo.dto.LoginDTO;
import com.xin.aggregateInfo.pojo.entity.AggregateAdmin;
import com.xin.aggregateInfo.service.AggregateAdminService;
import com.xin.constant.Constants;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.xin.utils.Response;
import com.xin.utils.UUIDUtils;

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
    public Response<Object> register(AggregateAdminDTO params) {
        // 获取验证码
        String code = (String) redisTemplate.opsForValue().get("generate:register:code");
        if (!params.getCode().equals(code)){
            return Response.error("验证码不正确，请重新获取");
        }
        QueryWrapper<AggregateAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", params.getUsername());
        AggregateAdmin admin = baseMapper.selectOne(queryWrapper);
        if (ObjectUtil.isNotEmpty(admin)){
            return Response.error("用户名重复，请重新输入！");
        }
        AggregateAdmin aggregateAdmin = new AggregateAdmin();
        BeanUtils.copyProperties(params,aggregateAdmin);
        aggregateAdmin.setId(UUIDUtils.getUUId());
        aggregateAdmin.setCreateTime(LocalDateTime.now());
        aggregateAdmin.setStatus(Constants.STATUS);
        aggregateAdmin.setSalt(getSalt());
        aggregateAdmin.setPassword(SecureUtil.md5(params.getPassword()+aggregateAdmin.getSalt()));
        baseMapper.insert(aggregateAdmin);
        return Response.success(aggregateAdmin);
    }

    /**
    * @author xin
    * @create 2021/11/23 3:21
    * @description 获取盐值
    **/
    private String getSalt(){
        return Md5Crypt.md5Crypt(RandomUtil.randomString(10).getBytes());
    }

    @Override
    public Response<Object> login(LoginDTO params){
        String code = (String) redisTemplate.opsForValue().get("generate:login:code");
        if (!params.getCode().equals(code)){
            return Response.error("验证码不正确");
        }
        QueryWrapper<AggregateAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", params.getUsername());
        AggregateAdmin admin = baseMapper.selectOne(queryWrapper);
        if (ObjectUtil.isEmpty(admin)){
            return Response.error("没有该用户的信息，请确认用户名是否正确");
        }
        if (!SecureUtil.md5(params.getPassword()+admin.getSalt()).equals(admin.getPassword())){
            return Response.error("密码错误，请重新输入！");
        }
        return Response.success(admin);
    }
}
