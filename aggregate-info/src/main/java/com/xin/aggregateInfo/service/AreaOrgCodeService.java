package com.xin.aggregateInfo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.aggregateInfo.pojo.dto.OrgCodeDTO;
import com.xin.aggregateInfo.pojo.entity.AreaOrgCode;
import com.xin.aggregateInfo.pojo.vo.OrgCodeVO;

import java.util.List;

/**
 * <p>
 * 地区编码	 服务类
 * </p>
 *
 * @author xin
 * @since 2022-03-06
 */
public interface AreaOrgCodeService extends IService<AreaOrgCode> {

    void jsonToSql(List<OrgCodeDTO> params);

    OrgCodeVO getOrgCodeTree(OrgCodeDTO params);
}
