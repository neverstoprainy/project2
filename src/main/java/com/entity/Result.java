package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author zym
 * @version 1.0
 * @description: 返回
 * @date 2024/5/30 23:50
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//响应码，1 代表成功; 0 代表失败
    private String message;  //响应信息 描述字符串
    private Object data; //返回的数据

    //增删改 成功响应
    public static Result success(){
        return new Result(0,"ok","null");
    }
    //查询 成功响应
    public static Result success(Object data){
        return new Result(40000,"ok",data);
    }
    //失败响应
    public static Result error(String msg){
        return new Result(40000,msg,"null");
    }
}
