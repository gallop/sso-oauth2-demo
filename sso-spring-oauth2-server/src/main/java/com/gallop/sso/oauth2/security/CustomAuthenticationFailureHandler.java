package com.gallop.sso.oauth2.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * author gallop
 * date 2021-06-24 18:00
 * Description:
 * Modified By:
 */
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 登录失败后，进行数据处理
        System.out.println("登录失败啦！！！");
        ObjectMapper objectMapper = new ObjectMapper();
        String exceptionStr = objectMapper.writeValueAsString(exception.getMessage());
        System.out.println(exceptionStr);

        request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION","登入错误啦～～～");
        // 转发到错误Url
        request.getRequestDispatcher("/login/error").forward(request,response);
        //request.setAttribute("error",true);
        // 跳转原页面
        //super.onAuthenticationFailure(request, response, exception);
    }
}
