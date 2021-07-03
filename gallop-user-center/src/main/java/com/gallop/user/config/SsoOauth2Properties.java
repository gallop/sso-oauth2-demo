package com.gallop.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "sso-oauth2")
public class SsoOauth2Properties {

    private String clientId; //应用id CLIENT_ID
    private String clientSecret; //应用secret
    private String oauthServerUrl;//获取授权地址
    private String oauthServerTokenUrl;//获取ACCESS_TOKEN换取地址
    private String oauthServiceApi;//获取用户信息接口地址
    private String oauthServerRedirectUri;//应用的回调地址
    private String bimRemoteUser;
    private String bimRemotePwd;
    private String  appId;
    private String[] rolePermissions;

}
