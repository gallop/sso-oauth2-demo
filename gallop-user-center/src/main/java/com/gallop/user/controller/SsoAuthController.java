package com.gallop.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.gallop.common.base.BaseResult;
import com.gallop.common.base.BaseResultUtil;
import com.gallop.user.config.SsoOauth2Properties;
import com.gallop.user.pojo.User;
import com.gallop.user.sso.SsoService;
import com.gallop.user.sso.SsoServiceRoute;
import com.gallop.user.sso.SsoTypeEnum;
import com.gallop.user.vo.AccessTokenVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * author gallop
 * date 2021-06-13 9:01
 * Description:
 * Modified By:
 */

@Controller
@RequestMapping("/sso")
@CrossOrigin
public class SsoAuthController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SsoOauth2Properties properties;

    @Autowired
    private SsoServiceRoute ssoServiceRoute;


    @RequestMapping(value = "/auth",method = RequestMethod.GET)
    public void auth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String state = "123";
        String url = properties.getOauthServerUrl() + "?client_id=" + properties.getClientId() + "&redirect_uri=" + properties.getOauthServerRedirectUri() + "&response_type=code&state=" + state;
        System.err.println("rul:" + url);
        response.sendRedirect(url);
    }


    @RequestMapping(value = "/getToken",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult getToken(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws OAuthSystemException, OAuthProblemException {
        System.err.println("getToken--code=="+user.getCode());
        if (StringUtils.isEmpty(user.getCode())) {
            return BaseResultUtil.failure("code 不能为空！");
        }
        BaseResult baseResult = null;
        try {
            SsoService ssoService = ssoServiceRoute.route(SsoTypeEnum.OAUTH2);
            baseResult = ssoService.login(user,request,response);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult = BaseResultUtil.failure("没有可以的sso service！");
        }

        return baseResult;
    }



}
