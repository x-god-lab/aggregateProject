package com.xin.aggregateInfo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.aggregateInfo.pojo.dto.AggregateAdminDTO;
import com.xin.aggregateInfo.pojo.dto.LoginDTO;
import com.xin.aggregateInfo.pojo.dto.OrgCodeDTO;
import com.xin.aggregateInfo.pojo.entity.AggregateAdmin;
import com.xin.utils.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
    Response<Object> register(AggregateAdminDTO params);

    /**
    * @author xin
    * @create 2021/11/21 14:58
    * @description 登录
    **/
    Response<String> login(LoginDTO params);

    /**
    * @author xin
    * @create 2022/1/16 16:22
    * @description 上传文件
    **/
    String upload(MultipartFile file) throws IOException;

    /**
    * @author xin
    * @create 2022/1/23 20:02
    * @description 删除文件
    **/
    String deleteFile(String fileName);

    void jsonToSql(List<OrgCodeDTO> params);
}
