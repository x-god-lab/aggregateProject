package com.xin.aggregateInfo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.aggregateInfo.pojo.dto.BookSourceDTO;
import com.xin.aggregateInfo.pojo.entity.BookSource;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xin
 * @since 2023-09-23 17:26:27
 */
public interface BookSourceService extends IService<BookSource> {

    /**
     * @Params @param params
     * @Return @return {@link Boolean }
     * @Description
     * @Throws
     * @Author xin
     * @Date 2023/09/23 21:54:32
     */
    Boolean analyseJson(BookSourceDTO params, MultipartFile file);

    /**
     * @Params
     * @Return
     * @Description 书源检测
     * @Throws
     * @Author xin
     * @Date 2023/09/23
     */
    void checkBookSource();

    /**
     * @Params
     * @Return @return {@link String }
     * @Description 书源返回
     * @Throws
     * @Author xin
     * @Date 2023/09/23 23:12:34
     */
    String getBookSourceJson();
}
