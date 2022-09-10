package com.xin.aggregateInfo.controller;


import com.github.pagehelper.PageInfo;
import com.xin.aggregateInfo.pojo.dto.MusicDTO;
import com.xin.aggregateInfo.pojo.dto.SaveMusicDTO;
import com.xin.aggregateInfo.pojo.entity.MusicInformation;
import com.xin.aggregateInfo.service.MusicInformationService;
import com.xin.constant.ReturnParameters;
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
 * @since 2022-01-14
 */
@RestController
@Api(tags = "音乐信息")
@RequestMapping("/musicInformation")
public class MusicInformationController {

    @Autowired
    private MusicInformationService musicInformationService;


    /**
    * @author xin
    * @create 2022/1/14 0:42
    * @description 保存歌曲
    **/
    @PostMapping("saveMusic")
    @ApiOperation("保存歌曲")
    public Response<String> saveMusic(@RequestBody SaveMusicDTO params){
        String result = musicInformationService.saveMusic(params);
        return Response.success(result);
    }

    /**
    * @author xin
    * @create 2022/1/14 1:07
    * @description 音乐列表
    **/
    @PostMapping("getList")
    @ApiOperation("音乐列表")
    public Response<PageInfo<MusicInformation>> getList(@RequestBody MusicDTO params){
        PageInfo<MusicInformation> pageInfo = musicInformationService.getList(params);
        return Response.success(ReturnParameters.SUCCESS,pageInfo);
    }

    /**
    * @author xin
    * @create 2022/1/14 2:16
    * @description 音乐下载
    **/
    @PostMapping("downLoadMusic")
    @ApiOperation("音乐下载")
    public void downLoadMusic(@RequestBody MusicDTO params){
        musicInformationService.downLoadMusic(params);
    }
}

