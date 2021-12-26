package com.xin.aggregateInfo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.xin.aggregateInfo.pojo.dto.JokeCollectionDTO;
import com.xin.aggregateInfo.pojo.entity.JokeCollection;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xin
 * @since 2021-12-11
 */
public interface JokeCollectionService extends IService<JokeCollection> {

    PageInfo<JokeCollection> getJokeList(JokeCollectionDTO params);
}
