package com.gallop.common.base;

import java.beans.Transient;
import java.io.Serializable;
import java.util.HashMap;

/**
 * author gallop
 * date 2021-06-18 14:31
 * Description: 自定义响应数据结构
 */
public class BaseResult<T> implements Serializable {
    private static final long serialVersionUID = 6169330890952202539L;

    // 响应业务状态
    private Integer code;
    // 响应消息
    private String msg;
    // 响应中的数据
    private T data;


    public BaseResult() {
    }

    public BaseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public BaseResult(String msg, T data) {
        this.code = ResponseCodeEnum.SUCCESS.getCode();
        this.msg = msg;
        this.data = data;
    }

    public BaseResult(T data) {
        this.code = ResponseCodeEnum.SUCCESS.getCode();
        this.msg = "OK";
        this.data = data;
    }

    //此注释表示此属性不参数系列化，是临时的
    @Transient
    public boolean isSuccess() {
        return ResponseCodeEnum.SUCCESS.getCode() == code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
