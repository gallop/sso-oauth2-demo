package com.gallop.common.base;

import java.util.HashMap;

/**
 * author gallop
 * date 2021-07-01 10:00
 * Description:
 * Modified By:
 */
public class BaseResultUtil {

    public static <T>BaseResult build(Integer code, String msg, T data) {
        return new BaseResult<>(code, msg, data);
    }

    public static BaseResult success() {
        return new BaseResult<>(null);
    }

    public static BaseResult success(final String message) {
        return new BaseResult(message, null);
    }

    public static <T>BaseResult success(T data) {
        BaseResult baseResult = new BaseResult(data);
        return baseResult;
    }

    public static <T> BaseResult success(final String message, final T data) {
        return new BaseResult<>(message, data);
    }

    public static BaseResult failure(final String message) {
        return new BaseResult(ResponseCodeEnum.FAIL.getCode(), message, null);

    }

    public static BaseResult failure(final int code, final String message) {
        return new BaseResult(code, message, null);

    }

    public static BaseResult unauthz(){
        return new BaseResult(ResponseCodeEnum.NOT_AUTH.getCode(), ResponseCodeEnum.NOT_AUTH.getMessage(), null);
    }

    public static BaseResult error() {
        return  new BaseResult(ResponseCodeEnum.SERVER_ERROR.getCode(),  ResponseCodeEnum.SERVER_ERROR.getMessage(), new HashMap<>());
    }

    public static BaseResult error(String message) {
        return  new BaseResult(ResponseCodeEnum.SERVER_ERROR.getCode(),  message, new HashMap<>());
    }

}
