package com.xin.aggregatesearch.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.pagehelper.PageInfo;
import com.xin.aggregatesearch.pojo.entity.EsEntity;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xin
 * @create 2022/03/22 0:52
 * @description
 **/
@Component
public class EsUtils {

    @Value("${es.host}")
    private String host;

    @Value("${es.port}")
    private String port;

    @Value("${es.scheme}")
    private String scheme;

    public static final String INDEX_NAME = "user_info";

    public static RestHighLevelClient client = null;


    /**
     * 创建es连接
     */
    @PostConstruct
    public void initClient() {
        try {
            if (client != null) {
                client.close();
            }
            String[] ports = port.split(",");
            HttpHost[] httpHosts = new HttpHost[ports.length];
            for (int i = 0; i < ports.length; i++) {
                httpHosts[i] = new HttpHost(host, Integer.parseInt(ports[i]), scheme);
            }
            client = new RestHighLevelClient(RestClient.builder(httpHosts));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Description: 判断某个index是否存在
     *
     * @param index index名
     * @return boolean
     * @author fanxb
     * @date 2019/7/24 14:57
     */
    public boolean indexExist(String index) throws Exception {
        GetIndexRequest request = new GetIndexRequest(index);
        request.local(false);
        request.humanReadable(true);
        request.includeDefaults(false);
        return client.indices().exists(request, RequestOptions.DEFAULT);
    }


    /**
     * Description: 搜索
     *
     * @param index   index
     * @param builder 查询参数
     * @param c       结果类对象
     * @return java.util.ArrayList
     * @author fanxb
     * @date 2019/7/25 13:46
     */
    public <T> PageInfo<T> search(String index, SearchSourceBuilder builder, Class<T> c) {
        SearchRequest request = new SearchRequest(index);
        request.source(builder);
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            List<T> res = new ArrayList<>(hits.length);
            for (SearchHit hit : hits) {
                res.add(JSON.parseObject(hit.getSourceAsString(),c));
            }
            // 获取总数
            long count = response.getHits().getTotalHits().value;
            PageInfo<T> pageInfo = new PageInfo<>();
            pageInfo.setList(res);
            // 判断是否需要多加一页
            int size = (int)count % builder.size();
            // 设置总页数
            if (size > 0){
                pageInfo.setPages((int) (count/builder.size()) + 1);
            }else {
                pageInfo.setPages((int) (count/builder.size()));
            }
            pageInfo.setTotal(count);
            return pageInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Description: 插入/更新一条记录
     *
     * @param index  index
     * @param entity 对象
     * @author fanxb
     * @date 2019/7/24 15:02
     */
    public void insertOrUpdateOne(String index, EsEntity entity) {
        IndexRequest request = new IndexRequest(index);
        request.id(entity.getId());
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            request.source(mapper.writeValueAsString(entity.getData()), XContentType.JSON);
            client.index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Description: 批量插入数据
     *
     * @param index index
     * @param list  带插入列表
     * @author fanxb
     * @date 2019/7/24 17:38
     */
    public void insertBatch(String index, List<EsEntity> list) {
        BulkRequest request = new BulkRequest();
        list.forEach(item -> request.add(new IndexRequest(index).id(item.getId())
                .source(JSON.toJSONString(item.getData()), XContentType.JSON)));
        try {
            client.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
