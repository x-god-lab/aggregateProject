package com.xin.aggregateInfo.service.impl;

import com.xin.aggregateInfo.pojo.entity.BookSource;
import com.xin.aggregateInfo.mappers.master.BookSourceMapper;
import com.xin.aggregateInfo.service.BookSourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xin
 * @since 2023-09-23 17:36:12
 */
@Service
public class BookSourceServiceImpl extends ServiceImpl<BookSourceMapper, BookSource> implements BookSourceService {

}
