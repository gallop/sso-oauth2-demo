package com.gallop.sso.oauth2.controller;


import com.gallop.common.base.BaseResultUtil;
import com.gallop.sso.oauth2.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * author gallop
 * date 2021-06-28 15:17
 * Description:
 * Modified By:
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private FindByIndexNameSessionRepository sessionRepository;

    @GetMapping("/list")
    public Map<String, ? extends Session> list(@RequestParam("username") String username) {
        return sessionRepository.findByPrincipalName(username);
    }

    @RequestMapping(value = "/setsession")
    public Object setSession(@RequestParam(required=false) String value, HttpSession session) {
        session.setAttribute("value", value);
        return session.getId();
    }

    @RequestMapping(value = "/getsession")
    public Object getSession(HttpSession session) {
        Object value = session.getAttribute("value");
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", session.getId());
        map.put("value", value);
        return map;
    }

    @PostMapping(value = "/getUserInfo")
    public Object getUserInfo(HttpServletRequest request, HttpServletResponse response){
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.err.println("user:"+userInfo.toString());
        //System.err.println("--request.getContentType():"+request.getContentType());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map map = new HashMap();
        map.put("loginName",userInfo.getUsername());
        map.put("fullName",userInfo.getSysUser().getNickname());
        return BaseResultUtil.success(map);
    }
}
