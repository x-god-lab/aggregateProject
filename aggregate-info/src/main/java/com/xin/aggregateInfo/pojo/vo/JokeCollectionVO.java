package com.xin.aggregateInfo.pojo.vo;

import com.xin.aggregateInfo.annotation.Secret;
import com.xin.aggregateInfo.annotation.vo.BaseVO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Secret(value = BaseVO.class)
public class JokeCollectionVO {

    private Integer id;

    private String content;

    private String createName;

    private String updateName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String encryptJson;
}
