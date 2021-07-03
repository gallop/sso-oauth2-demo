package com.gallop.sso.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author gallop
 * date 2021-06-15 11:47
 * Description:
 * Modified By:
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String login(HttpServletRequest request){
        System.err.println("----into login....");
        request.setAttribute("redirectUri", "http://localhost:8088/callback");
        request.setAttribute("appId", "app0001");
        return "/login";
    }
    @RequestMapping("/error")
    public String loginError(HttpServletRequest request, HttpServletResponse response, Model model) {
        response.setContentType("text/html;charset=utf-8");
        Object exception=request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");

        System.err.println(exception);
        model.addAttribute("error",exception);

        return "/login";
    }
}
