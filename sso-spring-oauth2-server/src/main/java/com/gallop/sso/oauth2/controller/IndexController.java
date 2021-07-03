package com.gallop.sso.oauth2.controller;


import com.gallop.common.base.BaseResult;
import com.gallop.common.base.BaseResultUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 首页管理
 * 
 * @author gallop
 */
@Controller
@RequestMapping("/")
public class IndexController {


	/**
	 * 初始页
	 * @param request
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
    @GetMapping
	public ModelAndView execute(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 设置登录用户名
		model.addAttribute("userName", "username");
		// 当前服务端口号
		model.addAttribute("serverPort", "8082");
		// 当前sessionId request.getSession().getId()
		model.addAttribute("sessionId", "12345");
		model.addAttribute("logoutUrl","");
		return new ModelAndView("/index");
	}
    
    /**
     * 获取当前应用访问路径
     *
     * @param request
     * @return
     */
    private String getLocalUrl(HttpServletRequest request) {
        StringBuilder url = new StringBuilder();
        url.append(request.getScheme()).append("://").append(request.getServerName());
        if (request.getServerPort() != 80 && request.getServerPort() != 443) {
            url.append(":").append(request.getServerPort());
        }
        url.append(request.getContextPath());
        return url.toString();
    }
}