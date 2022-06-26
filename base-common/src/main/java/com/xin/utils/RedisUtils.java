package com.xin.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xin
 * @create 2021/11/18 1:30
 * @description
 **/
@Slf4j
@Component
@SuppressWarnings("ALL")
public class RedisUtils {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public String get(String key){
        return key == null ? null : (String) redisTemplate.opsForValue().get(key);
    }

    public Object getObject(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public void set(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     *本来只可以放入string类型，但是我们配置了自动序列化所以这儿可以传入object
     */
    public void setex(String key,Object value,long expire){
        redisTemplate.opsForValue().set(key,value,expire, TimeUnit.SECONDS);
    }
    public  <T> List<T> ObjectToList(Object obj, Class<T> clazz){
        List<T> result = new ArrayList<T>();
        if(obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }
    public String ObjectToString(Object obj){
        return JSON.toJSONString(obj);
    }
    public <T> T StringToObject(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }
    public <T> List<T> StringToList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }
    /**
     *判断key是否存在
     */
    public boolean exists(String key){
        return redisTemplate.hasKey(key);
    }

    public boolean expire(String key,long expire){
        return redisTemplate.expire(key,expire, TimeUnit.SECONDS);
    }


    /**
     *查看key过期时间
     */
    public long ttl(String key){
        return redisTemplate.getExpire(key);
    }
    public void del(String ...keys){
        if(keys!=null&&keys.length>0) {
            redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(keys));
        }
    }
    public long incrBy(String key,long step){
        return redisTemplate.opsForValue().increment(key,step);
    }
    public boolean setnx(String key,Object value){
        return redisTemplate.opsForValue().setIfAbsent(key,value);
    }
    public boolean setnxAndExpire(String key,Object value,long expire){
        return redisTemplate.opsForValue().setIfAbsent(key,value,expire,TimeUnit.SECONDS);
    }
    public Object getAndSet(String key,Object value){
        return redisTemplate.opsForValue().getAndSet(key,value);
    }
}
