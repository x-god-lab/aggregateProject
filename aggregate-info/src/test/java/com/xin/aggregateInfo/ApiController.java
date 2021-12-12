package com.xin.aggregateInfo;

import com.alibaba.fastjson.JSON;
import com.xin.aggregateInfo.mapper.JokeCollectionMapper;
import com.xin.aggregateInfo.pojo.entity.JokeCollection;
import okhttp3.*;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
