package com.xin.aggregateInfo.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.ftp.Ftp;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.aggregateInfo.mapper.AggregateAdminMapper;
import com.xin.aggregateInfo.pojo.dto.AggregateAdminDTO;
import com.xin.aggregateInfo.pojo.dto.LoginDTO;
import com.xin.aggregateInfo.pojo.entity.AggregateAdmin;
import com.xin.aggregateInfo.pojo.upload.UploadParams;
import com.xin.aggregateInfo.service.AggregateAdminService;
import com.xin.constant.Constants;
import com.xin.utils.Response;
import com.xin.vo.AggregateAdminVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户信息表	 服务实现类
 * </p>
 *
 * @author xin
 * @since 2021-11-14
 */
@Service
@Slf4j
public class AggregateAdminServiceImpl extends ServiceImpl<AggregateAdminMapper, AggregateAdmin> implements AggregateAdminService {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private UploadParams params;

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
        aggregateAdmin.setId(IdUtil.fastSimpleUUID());
        aggregateAdmin.setCreateTime(DateTime.now());
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
    public Response<Map<String, String>> login(LoginDTO params) throws Exception {
        String code = (String) redisTemplate.opsForValue().get("generate:login:code");
        if (!params.getCode().equals(code)){
            return Response.error("验证码不正确");
        }
        SaTokenInfo saTokenInfo = this.login(params.getUsername(), params.getPassword());
        if (ObjectUtil.isEmpty(saTokenInfo)){
            throw new Exception("用户名或者密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>(2);
        tokenMap.put("token", saTokenInfo.getTokenValue());
        tokenMap.put("tokenHead", saTokenInfo.getTokenName());
        return Response.success(tokenMap);
    }

    /**
     * 账号密码校验
     */
    private SaTokenInfo login(String username,String password) throws Exception {
        QueryWrapper<AggregateAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        AggregateAdmin admin = baseMapper.selectOne(queryWrapper);
        if (ObjectUtil.isEmpty(admin)){
            throw new Exception("没有该用户的信息，请确认用户名是否正确");
        }
        if (!SecureUtil.md5(password+admin.getSalt()).equals(admin.getPassword())){
            throw new Exception("密码错误，请重新输入！");
        }

        AggregateAdminVO adminVO = BeanUtil.copyProperties(admin,AggregateAdminVO.class);
        List<AggregateAdminVO.RoleVO> roleList = baseMapper.getRoleList(admin.getRoleId());
        adminVO.setMenuIdList(roleList.stream().map(AggregateAdminVO.RoleVO::getMenuId).collect(Collectors.toList()));
        adminVO.setPermissionList(roleList.stream().map(AggregateAdminVO.RoleVO::getPerm).collect(Collectors.toList()));
        // 密码校验成功后登录，一行代码实现登录
        StpUtil.login(username);
        // 将用户信息存储到Session中
        StpUtil.getSession().set("admin",adminVO);
        // 获取当前用户的登录信息
        return StpUtil.getTokenInfo();
    }


    private Ftp getFtpConfig(){
        return new Ftp(params.getHost(),params.getPort(),params.getUsername(),params.getPassword());
    }

    @Override
    public String deleteFile(String fileName) {
        Ftp ftp = getFtpConfig();
        boolean existFile = ftp.existFile(fileName);
        if (!existFile){
            return "文件不存在";
        }
        boolean delFile = ftp.delFile(fileName);
        if (delFile){
            return "文件删除成功";
        }else {
            return "文件删除失败";
        }
    }
}
