package com.xin.aggregateInfo.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.aggregateInfo.listener.WeiboDataListener;
import com.xin.aggregateInfo.mappers.master.WeiboDataMapper;
import com.xin.aggregateInfo.pojo.dto.WeiboDataDTO;
import com.xin.aggregateInfo.pojo.entity.WeiboData;
import com.xin.aggregateInfo.service.WeiboDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

/**
 * <p>
 * 微博数据 服务实现类
 * </p>
 *
 * @author xin
 * @since 2022-02-20
 */
@Service
@Slf4j
public class WeiboDataServiceImpl extends ServiceImpl<WeiboDataMapper, WeiboData> implements WeiboDataService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importExcel(InputStream in, String author){
        EasyExcel
                .read(in, WeiboDataDTO.class,new WeiboDataListener(baseMapper,author))
                .excelType(ExcelTypeEnum.CSV)
                .sheet()
                .doRead();
        log.info("Excel导入成功");
    }
}
