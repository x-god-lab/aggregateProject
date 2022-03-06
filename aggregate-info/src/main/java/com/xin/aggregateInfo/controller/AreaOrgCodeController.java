package com.xin.aggregateInfo.controller;


import com.xin.aggregateInfo.pojo.dto.OrgCodeDTO;
import com.xin.aggregateInfo.pojo.vo.OrgCodeVO;
import com.xin.aggregateInfo.service.AreaOrgCodeService;
import com.xin.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 地区编码	 前端控制器
 * </p>
 *
 * @author xin
 * @since 2022-03-06
 */
@RestController
@Api(tags = "机构编码")
@RequestMapping("/areaOrgCode")
public class AreaOrgCodeController {

    @Autowired
    private AreaOrgCodeService orgCodeService;

    @ApiOperation("json转地区编码")
    @PostMapping("jsonToSql")
    public Response<String> jsonToSql(@RequestBody List<OrgCodeDTO> params){
        orgCodeService.jsonToSql(params);
        return Response.success("转换成功");
    }

    @ApiOperation("机构编码")
    @PostMapping("getOrgCodeTree")
    public Response<OrgCodeVO> getOrgCodeTree(@RequestBody OrgCodeDTO params){
        OrgCodeVO orgCodeVO = orgCodeService.getOrgCodeTree(params);
        return Response.success(orgCodeVO);
    }
}

