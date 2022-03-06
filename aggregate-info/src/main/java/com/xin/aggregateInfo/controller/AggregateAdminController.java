package com.xin.aggregateInfo.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.xin.aggregateInfo.pojo.dto.AggregateAdminDTO;
import com.xin.aggregateInfo.pojo.dto.LoginDTO;
import com.xin.aggregateInfo.pojo.dto.OrgCodeDTO;
import com.xin.aggregateInfo.pojo.entity.AggregateAdmin;
import com.xin.aggregateInfo.service.AggregateAdminService;
import com.xin.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户信息表	 前端控制器
 * </p>
 *
 * @author xin
 * @since 2021-11-14
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "用户信息控制类")
public class AggregateAdminController {

    @Autowired
    private AggregateAdminService aggregateAdminService;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @ApiOperation("注册")
    @PostMapping("register")
    public Response<Object> register(@RequestBody AggregateAdminDTO params){
        return aggregateAdminService.register(params);
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public Response<String> login(@RequestBody @Validated LoginDTO params){
        return aggregateAdminService.login(params);
    }

    @ApiOperation("生成图片验证码")
    @GetMapping("getShearCaptcha")
    public Response<String> getShearCaptcha(HttpServletRequest request, HttpServletResponse response) {

        OutputStream out = null;
        try {
            // 取得输出流
            out = response.getOutputStream();
            //定义图形验证码的长、宽、验证码字符数、干扰线宽度
            //定义图形验证码的长和宽
            LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
            redisTemplate.opsForValue().set("generate:login:code",lineCaptcha.getCode(),5, TimeUnit.MINUTES);
            //ShearCaptcha captcha = new ShearCaptcha(200, 100, 4, 4);
            //图形验证码写出，可以写出到文件，也可以写出到流
            lineCaptcha.write(out);
            //验证图形验证码的有效性，返回boolean值
            boolean checkPass = lineCaptcha.verify(lineCaptcha.getCode());
            // 将生成的验证码code放入session中
            if (checkPass){
                request.getSession().setAttribute("code", lineCaptcha.getCode());
            }
            out.flush();  // 将缓存中的数据立即强制刷新, 将缓冲区的数据输出到客户端浏览器
            out.close(); // 关闭输出流

            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Response.error("图片生成失败");
    }

    @ApiOperation("用户上传头像")
    @PostMapping("upload")
    @Transactional
    public Response<String> upload(@RequestParam("file") MultipartFile file,@RequestParam("id") String id) throws IOException {
        // 上传文件到FTP
        String result = aggregateAdminService.upload(file);
        // 保存文件到数据库
        AggregateAdmin admin = new AggregateAdmin();
        admin.setId(id);
        admin.setIcon(result);
        boolean updateById = aggregateAdminService.updateById(admin);
        if (updateById){
            return Response.success("文件上传成功",result);
        }
        return Response.error("文件上传失败");
    }

    @ApiOperation("json转地区编码")
    @PostMapping("jsonToSql")
    public Response<String> jsonToSql(@RequestBody List<OrgCodeDTO> params){
        aggregateAdminService.jsonToSql(params);
        return Response.success("转换成功");
    }
}

