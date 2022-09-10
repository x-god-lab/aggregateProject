package com.xin.aggregateInfo.controller;


import com.github.pagehelper.PageInfo;
import com.xin.aggregateInfo.pojo.dto.JokeCollectionDTO;
import com.xin.aggregateInfo.pojo.vo.JokeCollectionVO;
import com.xin.aggregateInfo.service.JokeCollectionService;
import com.xin.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xin
 * @since 2021-12-11
 */
@RestController
@RequestMapping("/jokeCollection")
@Api(tags = "笑话")
public class JokeCollectionController {

    @Autowired
    public JokeCollectionService jokeCollectionService;

    @PostMapping("getJokeList")
    @ApiOperation("笑话列表")
    public Response<PageInfo<JokeCollectionVO>> getJokeList(@RequestBody JokeCollectionDTO params){
        PageInfo<JokeCollectionVO> jokeList = jokeCollectionService.getJokeList(params);
        return Response.success("查询成功",jokeList);
    }
}

