package com.xin.aggregateInfo.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.aggregateInfo.mappers.master.BookSourceMapper;
import com.xin.aggregateInfo.pojo.dto.BookSourceDTO;
import com.xin.aggregateInfo.pojo.entity.BookSource;
import com.xin.aggregateInfo.service.BookSourceService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xin
 * @since 2023-09-23 17:26:27
 */
@Service
public class BookSourceServiceImpl extends ServiceImpl<BookSourceMapper, BookSource> implements BookSourceService {

    @Override
    @SneakyThrows
    public Boolean analyseJson(BookSourceDTO params,MultipartFile file) {
        List<BookSource> bookSources;
        if (StrUtil.isNotEmpty(params.getJsonAddress())){
            bookSources = JSON.parseArray(HttpUtil.get(params.getJsonAddress()), BookSource.class);
        }else {
            InputStream inputStream = file.getInputStream();
            Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            int ch = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                stringBuffer.append((char) ch);
            }
            inputStream.close();
            reader.close();
            bookSources = JSON.parseArray(stringBuffer.toString(), BookSource.class);
        }

        for (BookSource bookSource : bookSources) {
            baseMapper.insert(bookSource);
        }
        return true;
    }

    @Override
    public void checkBookSource() {
        List<BookSource> bookSources = baseMapper.selectList(new QueryWrapper<>());
        for (BookSource bookSource : bookSources) {
            try {
                HttpUtil.get(bookSource.getBookSourceUrl(),10000);
            }catch (Exception e){
                baseMapper.deleteById(bookSource);
            }
        }
    }

    @Override
    public String getBookSourceJson() {
        return JSON.toJSONString(baseMapper.selectList(new QueryWrapper<>()));
    }
}
