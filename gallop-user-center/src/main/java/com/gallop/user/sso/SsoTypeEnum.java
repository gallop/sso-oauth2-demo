package com.gallop.user.sso;

/**
 * author gallop
 * date 2021-06-17 16:29
 * Description:
 * Modified By:
 */
public enum SsoTypeEnum {
    OAUTH2("oauth2"), // oauth2 协议的sso
    OTHER("other"); // 其他的sso登录

    private final String value;

    SsoTypeEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
