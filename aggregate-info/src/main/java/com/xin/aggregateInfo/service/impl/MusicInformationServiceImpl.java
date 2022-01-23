package com.xin.aggregateInfo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mzlion.easyokhttp.HttpClient;
import com.xin.aggregateInfo.constant.Constants;
import com.xin.aggregateInfo.mapper.MusicInformationMapper;
import com.xin.aggregateInfo.pojo.dto.MusicDTO;
import com.xin.aggregateInfo.pojo.dto.SaveMusicDTO;
import com.xin.aggregateInfo.pojo.entity.MusicInformation;
import com.xin.aggregateInfo.service.MusicInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xin
 * @since 2022-01-14
 */
@Service
public class MusicInformationServiceImpl extends ServiceImpl<MusicInformationMapper, MusicInformation> implements MusicInformationService {

    @Autowired
    private MusicInformationMapper musicInformationMapper;

    @Override
    public String saveMusic(SaveMusicDTO params) {
        int insert = 0;
        for (int i = 0; i < params.getNum(); i++) {
            String param = HttpClient.post("https://api.uomg.com/api/rand.music")
                    .param("sort", params.getValue())
                    .param("format", "json")
                    .execute().asString();
            JSONObject json = new JSONObject(param);
            if (Constants.CODE.equals(json.get("code").toString())) {
                Object data = json.get("data");
                JSONObject jsonObject = new JSONObject(data.toString());
                QueryWrapper<MusicInformation> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("music_name", (String) jsonObject.get("name"));
                MusicInformation information = baseMapper.selectOne(queryWrapper);
                if (ObjectUtil.isNotEmpty(information)) {
                    continue;
                }
                MusicInformation musicInformation = new MusicInformation();
                musicInformation.setMusicName((String) jsonObject.get("name"));
                musicInformation.setMusicUrl((String) jsonObject.get("url"));
                musicInformation.setAuthor((String) jsonObject.get("artistsname"));
                musicInformation.setMusicPicurl((String) jsonObject.get("picurl"));
                musicInformation.setCreateTime(LocalDateTime.now());
                musicInformation.setUpdateTime(LocalDateTime.now());
                int result = baseMapper.insert(musicInformation);
                if (result > 0) {
                    insert++;
                }
            }else {
                return json.get("msg").toString();
            }
        }
        return "共增加了" + insert + "记录";
    }

    @Override
    public PageInfo<MusicInformation> getList(MusicDTO params) {
        PageHelper.startPage(params.getLimit(),params.getSize());
        List<MusicInformation> musicInformationList = musicInformationMapper.getList(params);
        return new PageInfo<>(musicInformationList);
    }

    @Override
    public void downLoadMusic(MusicDTO params) {
        if (StrUtil.isEmpty(params.getMusicName())){
            throw new RuntimeException("音乐名称不能为空");
        }
        QueryWrapper<MusicInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("music_name",params.getMusicName());
        MusicInformation musicInformation = baseMapper.selectOne(queryWrapper);
        try {
            URL url = new URL(musicInformation.getMusicUrl());
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            //文件爬完之后要放在哪里的地址
            FileOutputStream fileOutputStream = new FileOutputStream(musicInformation.getMusicName()+".mp3");
            byte[] bytes = new byte[1024];
            int len;
            while((len=inputStream.read(bytes))!=-1){
                fileOutputStream.write(bytes,0,len);
            }
            fileOutputStream.close();
            inputStream.close();
            urlConnection.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
