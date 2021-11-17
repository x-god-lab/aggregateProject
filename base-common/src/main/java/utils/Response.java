package utils;

import exception.ExceptionEnum;
import lombok.Data;

/**
 * @author xin
 * @create 2021/11/15 1:13
 * @description
 **/
@Data
public class Response<T> {
    private Integer status;
    private String msg;
    private T data;

    public Response(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Response(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    /**
    * @author xin
    * @create 2021/11/15 1:34
    * @description 状态码+成功提示信息
    **/
    public static <T> Response<T> success(String msg){
        return new Response<>(ExceptionEnum._200.getCode(),msg);
    }

    /**
     * @author xin
     * @create 2021/11/15 1:34
     * @description 状态码+成功提示信息+数据
     **/
    public static <T> Response<T> success(String msg,T data){
        return new Response<>(ExceptionEnum._200.getCode(),msg,data);
    }

    /**
     * @author xin
     * @create 2021/11/15 1:34
     * @description 状态码+错误提示信息
     **/
    public static <T> Response<T> error(String msg){
        return new Response<>(ExceptionEnum._400.getCode(),msg);
    }
}
