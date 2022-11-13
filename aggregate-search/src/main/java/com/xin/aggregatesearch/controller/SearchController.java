package com.xin.aggregatesearch.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.xin.aggregatesearch.pojo.dto.UserInfoDTO;
import com.xin.aggregatesearch.pojo.entity.EsEntity;
import com.xin.aggregatesearch.pojo.entity.UserInfo;
import com.xin.aggregatesearch.pojo.vo.UserInfoVO;
import com.xin.aggregatesearch.utils.EsUtils;
import com.xin.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xin
 * @create 2022/02/20 22:25
 * @description
 **/
@RestController
@Api(tags = "查询服务")
@RequestMapping("/search")
public class SearchController{

    @Autowired
    private EsUtils esUtils;


    /**
     * @param params
     * 详情查询
     */
    @ApiOperation("详情查询")
    @PostMapping("info")
    public Response<UserInfoVO> getById(@RequestBody UserInfoDTO params) {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("id",params.getId()));
        PageInfo<UserInfoVO> pageInfo = esUtils.search(EsUtils.INDEX_NAME, builder, UserInfoVO.class);
        return Response.success(pageInfo.getList().get(0));
    }

    /**
    * @author xin
    * @create 2022/3/23 1:03
    * @description 分页查询
    **/
    @ApiOperation("分页查询")
    @PostMapping("getUserInfoVOList")
    public Response<PageInfo<UserInfoVO>> getUserInfoVOList(@RequestBody UserInfoDTO params){
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(params.getForm());
        builder.size(params.getSize());
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        if (StrUtil.isNotEmpty(params.getName())){
            WildcardQueryBuilder queryBuilder = QueryBuilders.wildcardQuery("NAME.keyword","*"+params.getName()+"*");
            query.must(queryBuilder);
        }
        if (StrUtil.isNotEmpty(params.getIdCardNum())){
            query.must(QueryBuilders.termQuery("id_card_num", params.getIdCardNum()));
        }
        if (StrUtil.isNotEmpty(params.getCellPhone())){
            query.must(QueryBuilders.termQuery("cell_phone", params.getCellPhone()));
        }
        builder.query(query);
        builder.sort("update_time", SortOrder.DESC);
        return Response.success(esUtils.search(EsUtils.INDEX_NAME, builder, UserInfoVO.class));
    }

    /**
    * @author xin
    * @create 2022/3/28 0:58
    * @description TODO
    **/
    @ApiOperation("插入或者更新一条数据")
    @PostMapping("insertOrUpdate")
    public Response<String> insertOrUpdate(@RequestBody UserInfo params){
        EsEntity<UserInfo> entity = new EsEntity<>();
        params.setUpdateTime(DateTime.now());
        params.setCreateTime(DateTime.now());
        params.setBirth(DateTime.now());
        entity.setId(params.getId().toString());
        entity.setData(params);
        esUtils.insertOrUpdateOne(EsUtils.INDEX_NAME,entity);
        return Response.success("修改成功");
    }
}
