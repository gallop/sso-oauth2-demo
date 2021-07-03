package com.gallop.common.base;

/**
 * author gallop
 * date 2021-06-18 14:28
 * Description:
 * Modified By:
 */
public enum ResponseCodeEnum {
    SUCCESS(200, "请求成功"),
    FAIL(400, "操作失败"),
    NOT_FOUND(404, "请求不存在"),
    SERVER_ERROR(500, "服务异常"),
    NOT_AUTH(401,"未授权"),
    BIND_ERROR(402,"参数校验异常：%s");

    private int code;
    private String message;
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
