package com.xin.aggregateInfo;

import com.alibaba.fastjson.JSON;
import com.mzlion.easyokhttp.HttpClient;
import com.xin.aggregateInfo.mappers.master.JokeCollectionMapper;
import com.xin.aggregateInfo.mappers.master.MusicInformationMapper;
import com.xin.aggregateInfo.pojo.entity.JokeCollection;
import com.xin.aggregateInfo.pojo.entity.MusicInformation;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xin
 * @create 2021/12/11 22:16
 * @description
 **/
@SpringBootTest
public class ApiController {

    @Autowired
    private JokeCollectionMapper jokeCollectionMapper;

    @Autowired
    private MusicInformationMapper musicInformationMapper;

    /**
    * @author xin
    * @create 2021/12/11 23:54
    * @description 笑话大全Api调用
    **/
    @Test
    public void test() throws Exception {
        String url = "https://api.apishop.net/common/joke/getJokesByRandom";
        for (int i = 0; i < 15; i++) {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("apiKey","tjqAYutd455a9ff1dc933d7569ff68861cd892ea5285a6f")
                    .add("pageSize","20").build();
            Request request = new Request.Builder()
                    .url(url).post(requestBody).build();
            Response response = client.newCall(request).execute();
            String data = response.body().string();
            JSONObject jsonObject = new JSONObject(data);
            List<JokeCollection> result = JSON.parseArray(jsonObject.get("result").toString(), JokeCollection.class);
            for (JokeCollection jokeCollection : result) {
                jokeCollection.setId(null);
                jokeCollection.setCreateName("xin");
                jokeCollection.setUpdateName("xin");
                jokeCollection.setCreateTime(LocalDateTime.now());
                jokeCollection.setUpdateTime(LocalDateTime.now());
                jokeCollectionMapper.insert(jokeCollection);
            }
            System.out.println(i);
        }
    }

    @Test
    public void saveMusic() throws JSONException {
        int insert = 0;
        for (int i = 0; i < 100; i++) {
            String param = HttpClient.post("https://api.uomg.com/api/rand.music")
//                .param("mid","1909956702")
                    .param("sort","热歌榜")
                    .param("format","json")
                    .execute().asString();
            JSONObject json = new JSONObject(param);
            Object data = json.get("data");
            JSONObject jsonObject = new JSONObject(data.toString());
            MusicInformation musicInformation = new MusicInformation();
            musicInformation.setMusicName((String) jsonObject.get("name"));
            musicInformation.setMusicUrl((String) jsonObject.get("url"));
            musicInformation.setAuthor((String) jsonObject.get("artistsname"));
            musicInformation.setMusicPicurl((String) jsonObject.get("picurl"));
            musicInformation.setCreateTime(LocalDateTime.now());
            musicInformation.setUpdateTime(LocalDateTime.now());
            int result = musicInformationMapper.insert(musicInformation);
            if (result > 0){
                insert++;
            }
        }
        System.out.println("共增加了"+insert+"记录");
    }

    @Test
    public void downLoadMusic() throws Exception {
        URL url = new URL("http://music.163.com/song/media/outer/url?id=1846489646");
        HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        //文件爬完之后要放在哪里的地址
        FileOutputStream fileOutputStream = new FileOutputStream("嘉宾.mp3");
        byte[] bytes = new byte[1024];
        int len;
        while((len=inputStream.read(bytes))!=-1){
            fileOutputStream.write(bytes,0,len);
        }
        fileOutputStream.close();
        inputStream.close();
        urlConnection.disconnect();
    }
}
