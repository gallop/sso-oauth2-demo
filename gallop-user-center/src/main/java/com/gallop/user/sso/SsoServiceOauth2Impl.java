package com.gallop.user.sso;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gallop.common.base.BaseResult;
import com.gallop.common.base.BaseResultUtil;
import com.gallop.common.base.JsonUtils;
import com.gallop.user.config.SsoOauth2Properties;
import com.gallop.user.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * author gallop
 * date 2021-06-17 16:51
 * Description:
 * Modified By:
 */
@Service
@Slf4j
public class SsoServiceOauth2Impl extends SsoServiceBasic {

    @Autowired
    private SsoOauth2Properties properties;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public SsoTypeEnum ssoType() {
        return SsoTypeEnum.OAUTH2;
    }

    @Override
    public BaseResult login(User user, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(user.getCode())) {
            return BaseResultUtil.failure("code 不能为空！");
        }
        String accessToken = getAccessToken(user.getCode());
        if (StringUtils.isEmpty(accessToken)) {
            return BaseResultUtil.failure("获取accessToken失败！");
        }
        String userAccount = getUserAccount(accessToken);
        if (StringUtils.isEmpty(userAccount)) {
                return BaseResultUtil.failure("获取用户信息失败！");
        }
        user.setLoginName(userAccount);

        BaseResult baseResult = returnspot(user, request, response);
        return baseResult;
    }
    /**
     * date @2021-06-16 16:50
     * Description: 从统一认证中心 获取 AccessToken
     **/
    private String getAccessToken(String code) {
        String accessToken = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", properties.getClientId());
        map.add("client_secret", properties.getClientSecret());
        map.add("grant_type", "authorization_code");
        map.add("redirect_uri", properties.getOauthServerRedirectUri());
        map.add("code", code);
        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(properties.getOauthServerTokenUrl(), req, String.class);

        JSONObject accessTokenJSON = JSON.parseObject(response.getBody());
        if (accessTokenJSON.containsKey("access_token")) {
            accessToken = (String) accessTokenJSON.get("access_token");
        }

        return accessToken;
    }

    /**
     * date @2021-06-16 16:51
     * Description: 从同一认证中心 获取用户信息
     **/
    private String getUserAccount(String accessToken) {
        String userAccount = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> userInfoReq = new HttpEntity<>(null, headers);
        String serviceApi = properties.getOauthServiceApi()+ "?client_id=" + properties.getClientId() + "&access_token=" + accessToken;
        ResponseEntity<String> userResponse = restTemplate.exchange(serviceApi, HttpMethod.POST, userInfoReq, String.class);
        log.info("getUserInfo:" + userResponse.getBody());

        BaseResult<Map> baseResult = JsonUtils.jsonToPojo(userResponse.getBody(),BaseResult.class);
        System.err.println("loginname:"+baseResult.getData().get("loginName"));
        userAccount = (String)baseResult.getData().get("loginName");
        /*JSONObject userJSON = JSON.parseObject(userResponse.getBody());
        if (userJSON.containsKey("data")) {
            JSONObject data = JSON.parseObject(""+userJSON.get("data"));
            System.err.println("loginName:"+user.getLoginName());
           *//* String userStr = (String) userJSON.get("data");
            User user = JSONObject.parseObject(userStr,User.class);
            System.err.println("loginName:"+user.getLoginName());*//*
        }*/
        return userAccount;
    }

    /*@Override
    public void afterPropertiesSet() throws Exception {
        SsoServiceFactory.register(ssoType(),this);
    }*/
}
