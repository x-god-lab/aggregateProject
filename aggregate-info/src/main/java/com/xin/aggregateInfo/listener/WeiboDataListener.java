package com.xin.aggregateInfo.listener;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.xin.aggregateInfo.mappers.master.WeiboDataMapper;
import com.xin.aggregateInfo.pojo.dto.WeiboDataDTO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xin
 * @create 2022/02/20 2:19
 * @description
 **/
@Slf4j
@NoArgsConstructor
public class WeiboDataListener extends AnalysisEventListener<WeiboDataDTO> {
    /**
     * @author xin
     * @create 2022/2/20 2:01
     * @description 每隔100条存储
     **/
    public static final int BATCH_COUNT = 100;

    /**
     * @author xin
     * @create 2022/2/20 2:00
     * @description 缓存的数据
     **/
    public static final List<WeiboDataDTO> CATCH_WEIBO_DATA = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    /**
    * @author xin
    * @create 2022/2/20 3:02
    * @description 作者
    **/
    public String author = null;

    private WeiboDataMapper weiboDataMapper;

    public WeiboDataListener(WeiboDataMapper weiboDataMapper,String author){
        this.weiboDataMapper = weiboDataMapper;
        this.author = author;
    }

    @Override
    public void invoke(WeiboDataDTO weiboDataDTO, AnalysisContext analysisContext) {
        log.info("解析到一条记录:{}",weiboDataDTO);
        CATCH_WEIBO_DATA.add(weiboDataDTO);
        // 当缓存的数据达到100，需要去存储一次数据库
        if (CATCH_WEIBO_DATA.size() >= BATCH_COUNT){
            saveData();
            CATCH_WEIBO_DATA.clear();
        }
    }

    private void saveData() {
        log.info("{}条数据存储到数据库",CATCH_WEIBO_DATA.size());
        if (CollUtil.isNotEmpty(CATCH_WEIBO_DATA)){
            CATCH_WEIBO_DATA.forEach(weiboDataDTO -> {
                weiboDataDTO.setId(IdUtil.fastUUID());
                weiboDataDTO.setCreateTime(LocalDateTime.now());
                weiboDataDTO.setUpdateTime(LocalDateTime.now());
                weiboDataDTO.setAuthor(author);
            });
            weiboDataMapper.insertBatch(CATCH_WEIBO_DATA);
        }
        log.info("{}条数据保存成功",CATCH_WEIBO_DATA.size());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 剩余的记录不足100条时，直接保存
        saveData();
        log.info("所有数据解析完成");
    }
}
