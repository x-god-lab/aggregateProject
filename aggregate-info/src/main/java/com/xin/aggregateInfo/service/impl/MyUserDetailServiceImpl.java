package com.xin.aggregateInfo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xin.aggregateInfo.mapper.AggregateAdminMapper;
import com.xin.aggregateInfo.pojo.entity.AggregateAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xin
 * @create 2021/11/24 2:15
 * @description
 **/
@Service("userDetailService")
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AggregateAdminMapper aggregateAdminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<AggregateAdmin> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        AggregateAdmin admin = aggregateAdminMapper.selectOne(wrapper);
        if (ObjectUtil.isEmpty(admin)){
            throw new UsernameNotFoundException("没有该用户的信息，请确认用户名是否正确");
        }

        List<GrantedAuthority> role = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
        return new User(username,new BCryptPasswordEncoder().encode(admin.getPassword()),role);
    }
}
