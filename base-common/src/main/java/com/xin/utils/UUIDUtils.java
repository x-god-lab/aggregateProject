package com.xin.utils;

import java.util.UUID;

/**
 * @author xin
 * @create 2021/11/18 1:43
 * @description
 **/
public class UUIDUtils {

    public static String getUUId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
