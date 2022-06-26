package com.xin.aggregateInfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.aggregateInfo.pojo.entity.AreaOrgCode;
import com.xin.aggregateInfo.pojo.vo.AreaOrgCodeVO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 地区编码	 Mapper 接口
 * </p>
 *
 * @author xin
 * @since 2022-03-06
 */
@Repository
public interface AreaOrgCodeMapper extends BaseMapper<AreaOrgCode> {

    /**
    * @author xin
    * @create 2022/3/6 14:26
    * @description
    **/
    List<AreaOrgCodeVO> getTreeList(@Param("parentCode") String parentCode);
}
