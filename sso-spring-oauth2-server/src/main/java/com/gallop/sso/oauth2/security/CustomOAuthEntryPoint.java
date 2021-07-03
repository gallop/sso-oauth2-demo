package com.gallop.sso.oauth2.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gallop.common.base.BaseResult;
import com.gallop.common.base.BaseResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * author gallop
 * date 2021-07-01 11:21
 * Description: resource服务器请求，验证token失败(未带token/token失效)时返回值重写
 * Modified By:
 */
@Component
@Slf4j
public class CustomOAuthEntryPoint implements AuthenticationEntryPoint {
    @Autowired
    private ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Throwable cause = authException.getCause();
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            if (cause instanceof OAuth2AccessDeniedException) {
                // 资源权限不足
                BaseResult resp = BaseResultUtil.build(401,"权限不足",null);
                response.getWriter().write(mapper.writeValueAsString(resp));
            } else if (cause == null || cause instanceof InvalidTokenException) {
                // 未带token或token无效
                // cause == null 一般可能是未带token
                BaseResult resp = BaseResultUtil.build(401,"未带token或token无效",null);
                response.getWriter().write(mapper.writeValueAsString(resp));
            }else {

                System.err.println("cause:"+cause.getClass().getName());
            }
        } catch (IOException e) {
            log.error("其他异常error", e);
            throw new RuntimeException(e);
        }
    }
}
