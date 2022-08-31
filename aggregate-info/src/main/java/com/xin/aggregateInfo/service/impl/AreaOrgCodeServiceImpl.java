package com.xin.aggregateInfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.aggregateInfo.mappers.master.AreaOrgCodeMapper;
import com.xin.aggregateInfo.pojo.dto.AreaOrgCodeDTO;
import com.xin.aggregateInfo.pojo.entity.AreaOrgCode;
import com.xin.aggregateInfo.pojo.vo.AreaOrgCodeVO;
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
    public void jsonToSql(List<AreaOrgCodeDTO> params) {
        for (AreaOrgCodeDTO param : params) {
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
    public AreaOrgCodeVO getOrgCodeTree(AreaOrgCodeDTO params) {
        AreaOrgCodeVO orgCodeVO = new AreaOrgCodeVO();
        AreaOrgCode areaOrgCode = baseMapper.selectById(params.getCode());
        BeanUtil.copyProperties(areaOrgCode,orgCodeVO);
        List<AreaOrgCodeVO> childrenCode = getChildrenCode(areaOrgCode.getOrgCode());
        orgCodeVO.setChildren(childrenCode);
        return orgCodeVO;
    }

    private List<AreaOrgCodeVO> getChildrenCode(String orgCode){
        List<AreaOrgCodeVO> orgCodes = orgCodeMapper.getTreeList(orgCode);
        if (CollUtil.isNotEmpty(orgCodes)){
            for (AreaOrgCodeVO code : orgCodes) {
                List<AreaOrgCodeVO> childrenCode = getChildrenCode(code.getOrgCode());
                code.setChildren(childrenCode);
            }
        }
        return orgCodes;
    }
}
