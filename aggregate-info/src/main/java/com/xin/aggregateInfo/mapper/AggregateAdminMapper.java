package com.xin.aggregateInfo.mapper;

import com.xin.aggregateInfo.pojo.entity.AggregateAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.vo.AggregateAdminVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户信息表	 Mapper 接口
 * </p>
 *
 * @author xin
 * @since 2021-11-14
 */
@Repository
public interface AggregateAdminMapper extends BaseMapper<AggregateAdmin> {
    /**
     * 获取用户的菜单权限
     * @param roleId
     * @return
     */
    List<AggregateAdminVO.RoleVO> getRoleList(String roleId);
}
