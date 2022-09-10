package com.xin.aggregateInfo.mappers.master;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.aggregateInfo.pojo.dto.MusicDTO;
import com.xin.aggregateInfo.pojo.entity.MusicInformation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xin
 * @since 2022-01-14
 */
@Repository
public interface MusicInformationMapper extends BaseMapper<MusicInformation> {

    List<MusicInformation> getList(MusicDTO params);
}
