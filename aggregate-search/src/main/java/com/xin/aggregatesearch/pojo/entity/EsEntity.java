package com.xin.aggregatesearch.pojo.entity;

import lombok.Data;

/**
 * @author xin
 * @create 2022/03/22 1:02
 * @description
 **/
@Data
public final class EsEntity<T> {

    private String id;
    private T data;
}
