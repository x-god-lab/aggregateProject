package com.xin.aggregateInfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.aggregateInfo.mapper.AreaOrgCodeMapper;
import com.xin.aggregateInfo.pojo.dto.OrgCodeDTO;
import com.xin.aggregateInfo.pojo.entity.AreaOrgCode;
import com.xin.aggregateInfo.pojo.vo.OrgCodeVO;
import com.xin.aggregateInfo.service.AreaOrgCodeService;
import com.xin.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 地区编码	 服务实现类
 * </p>
 *
 * @author xin
 * @since 2022-03-06
 */
@Service
public class AreaOrgCodeServiceImpl extends ServiceImpl<AreaOrgCodeMapper, AreaOrgCode> implements AreaOrgCodeService {

    @Autowired
    private AreaOrgCodeMapper orgCodeMapper;

    @Override
    public void jsonToSql(List<OrgCodeDTO> params) {
        for (OrgCodeDTO param : params) {
            AreaOrgCode areaOrgCode = new AreaOrgCode();
            areaOrgCode.setOrgCode(param.getCode());
            areaOrgCode.setOrgName(param.getName());
            areaOrgCode.setCreateTime(LocalDateTime.now());
            areaOrgCode.setUpdateTime(LocalDateTime.now());
            if (StrUtil.length(areaOrgCode.getOrgCode()) == Constants.SHORT_CODE){
                areaOrgCode.setParentCode("0");
            }else if (StrUtil.length(areaOrgCode.getOrgCode()) == Constants.MEDIUM_CODE){
                areaOrgCode.setParentCode(StrUtil.sub(areaOrgCode.getOrgCode(),0,2));
            }else if (StrUtil.length(areaOrgCode.getOrgCode()) == Constants.LONG_CODE){
                areaOrgCode.setParentCode(StrUtil.sub(areaOrgCode.getOrgCode(),0,4));
            }else if (StrUtil.length(areaOrgCode.getOrgCode()) == Constants.MAX_LONG_CODE){
                areaOrgCode.setParentCode(StrUtil.sub(areaOrgCode.getOrgCode(),0,6));
            }
            baseMapper.insert(areaOrgCode);
            if (CollUtil.isNotEmpty(param.getChildren())){
                jsonToSql(param.getChildren());
            }
        }
    }

    @Override
    public OrgCodeVO getOrgCodeTree(OrgCodeDTO params) {
        OrgCodeVO orgCodeVO = new OrgCodeVO();
        AreaOrgCode areaOrgCode = baseMapper.selectById(params.getCode());
        BeanUtil.copyProperties(areaOrgCode,orgCodeVO);
        List<OrgCodeVO> childrenCode = getChildrenCode(areaOrgCode.getOrgCode());
        orgCodeVO.setChildren(childrenCode);
        return orgCodeVO;
    }

    private List<OrgCodeVO> getChildrenCode(String orgCode){
        List<OrgCodeVO> orgCodes = orgCodeMapper.getTreeList(orgCode);
        if (CollUtil.isNotEmpty(orgCodes)){
            for (OrgCodeVO code : orgCodes) {
                List<OrgCodeVO> childrenCode = getChildrenCode(code.getOrgCode());
                code.setChildren(childrenCode);
            }
        }
        return orgCodes;
    }
}
