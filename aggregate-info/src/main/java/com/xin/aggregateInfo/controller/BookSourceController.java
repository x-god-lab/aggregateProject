package com.xin.aggregateInfo.controller;

import com.xin.aggregateInfo.pojo.dto.BookSourceDTO;
import com.xin.aggregateInfo.service.BookSourceService;
import com.xin.utils.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xin
 * @since 2023-09-23 17:26:27
 */
@RestController
@RequestMapping("/bookSource")
public class BookSourceController {

    @Autowired
    private BookSourceService bookSourceService;

    @PostMapping("analyseJson")
    @ApiOperation("解析Json文件")
    public Response<Boolean> analyseJson(BookSourceDTO params, MultipartFile file){
        return Response.success(bookSourceService.analyseJson(params,file));
    }

    @PostMapping("checkBookSource")
    @ApiOperation("书源检测")
    public void checkBookSource(){
        bookSourceService.checkBookSource();
    }

    @GetMapping("getBookSourceJson")
    @ApiOperation("书源返回")
    public String getBookSourceJson(){
        return bookSourceService.getBookSourceJson();
    }
}
