package com.xin.aggregateInfo.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JokeCollectionVO {

    private Integer id;

    private String content;

    private String createName;

    private String updateName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String encryptJson;
}
