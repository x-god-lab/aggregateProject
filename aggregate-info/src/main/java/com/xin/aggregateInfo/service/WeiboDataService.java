package com.xin.aggregateInfo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.aggregateInfo.pojo.entity.WeiboData;

import java.io.InputStream;

/**
 * <p>
 * 微博数据 服务类
 * </p>
 *
 * @author xin
 * @since 2022-02-20
 */
public interface WeiboDataService extends IService<WeiboData> {

    /**
    * @author xin
    * @create 2022/2/20 2:16
    * @description 导入Excel数据
    **/
    void importExcel(InputStream in, String author);
}
