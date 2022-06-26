package com.xin.aggregateInfo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.xin.aggregateInfo.pojo.dto.MusicDTO;
import com.xin.aggregateInfo.pojo.dto.SaveMusicDTO;
import com.xin.aggregateInfo.pojo.entity.MusicInformation;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xin
 * @since 2022-01-14
 */
public interface MusicInformationService extends IService<MusicInformation> {

    /**
    * @author xin
    * @create 2022/1/14 0:44
    * @description 保存歌曲
    *
     * @param params*/
    String saveMusic(SaveMusicDTO params);

    /**
    * @author xin
    * @create 2022/1/14 1:17
    * @description 音乐列表
    **/
    PageInfo<MusicInformation> getList(MusicDTO params);

    /**
    * @author xin
    * @create 2022/1/14 2:17
    * @description 下载歌曲
    **/
    void downLoadMusic(MusicDTO params);

}
