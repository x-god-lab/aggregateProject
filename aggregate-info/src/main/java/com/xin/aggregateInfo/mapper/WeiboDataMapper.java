package com.xin.aggregateInfo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.aggregateInfo.pojo.dto.WeiboDataDTO;
import com.xin.aggregateInfo.pojo.entity.WeiboData;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 * 微博数据 Mapper 接口
 * </p>
 *
 * @author xin
 * @since 2022-02-20
 */
public interface WeiboDataMapper extends BaseMapper<WeiboData> {

    void insertBatch(@Param("data") List<WeiboDataDTO> data);
}
