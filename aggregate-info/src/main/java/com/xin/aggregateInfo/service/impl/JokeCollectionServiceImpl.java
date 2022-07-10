package com.xin.aggregateInfo.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.aggregateInfo.mapper.JokeCollectionMapper;
import com.xin.aggregateInfo.pojo.dto.JokeCollectionDTO;
import com.xin.aggregateInfo.pojo.entity.JokeCollection;
import com.xin.aggregateInfo.pojo.vo.JokeCollectionVO;
import com.xin.aggregateInfo.service.JokeCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xin
 * @since 2021-12-11
 */
@Service
public class JokeCollectionServiceImpl extends ServiceImpl<JokeCollectionMapper, JokeCollection> implements JokeCollectionService {

    @Autowired
    private JokeCollectionMapper jokeCollectionMapper;

    @Override
    public PageInfo<JokeCollectionVO> getJokeList(JokeCollectionDTO params) {
        PageHelper.startPage(params.getLimit(),params.getSize());
        QueryWrapper<JokeCollection> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("content",params.getContent());
        List<JokeCollection> collectionList = jokeCollectionMapper.selectList(queryWrapper);
        List<JokeCollectionVO> list = new ArrayList<>();
        CollUtil.addAll(list,collectionList);
        return new PageInfo<>(list);
    }
}
