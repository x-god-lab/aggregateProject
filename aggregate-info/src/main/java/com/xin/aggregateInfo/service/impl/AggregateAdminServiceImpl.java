package com.xin.aggregateInfo.service.impl;

import cn.hutool.core.date.DatePattern;
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
import com.xin.aggregateInfo.mapper.AreaOrgCodeMapper;
import com.xin.aggregateInfo.pojo.dto.AggregateAdminDTO;
import com.xin.aggregateInfo.pojo.dto.LoginDTO;
import com.xin.aggregateInfo.pojo.entity.AggregateAdmin;
import com.xin.aggregateInfo.pojo.upload.UploadParams;
import com.xin.aggregateInfo.service.AggregateAdminService;
import com.xin.constant.Constants;
import com.xin.utils.JwtUtil;
import com.xin.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private AreaOrgCodeMapper orgCodeMapper;

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
    public Response<String> login(LoginDTO params){
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
        String token = JwtUtil.getToken(params.getUsername(),params.getPassword());
        assert token != null;
        redisTemplate.opsForValue().set("token",token,15, TimeUnit.MINUTES);
        return Response.success("登录成功",token);
    }

    private Ftp getFtpConfig(){
        return new Ftp(params.getHost(),params.getPort(),params.getUsername(),params.getPassword());
    }

    @Override
    public String upload(MultipartFile file) throws IOException {
        Ftp ftp = getFtpConfig();
        String pathName = DateUtil.format(DateUtil.date(), DatePattern.NORM_DATE_PATTERN);
        boolean exist = ftp.exist(params.getFilePath() + pathName);
        if (!exist){
            boolean mkdir = ftp.mkdir(pathName);
            if (!mkdir){
                return "创建文件夹失败";
            }
        }
        String filename = file.getOriginalFilename();
        String subAfter = StrUtil.subAfter(filename, ".", true);
        filename = IdUtil.fastSimpleUUID()+"."+subAfter;
        boolean upload = ftp.upload(pathName, filename, file.getInputStream());
        ftp.close();
        if (upload){
            return params.getFilePath()+pathName+"/"+filename;
        }else {
            return null;
        }
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
