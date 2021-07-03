package com.gallop.sso.oauth2.controller;


import com.gallop.common.base.BaseResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

/**
 * author gallop
 * date 2021-06-29 11:14
 * Description:
 * Modified By:
 */
//@Controller
@FrameworkEndpoint
public class RevokeTokenEndpoint {
    @Autowired
    @Qualifier("consumerTokenServices")
    ConsumerTokenServices consumerTokenServices;

    @RequestMapping(method = RequestMethod.GET, value = "/oauth/revokeToken")
    @ResponseBody
    public Object revokeToken(@RequestParam String access_token) {
        System.err.println("revoke-access_token:"+access_token);
        if (consumerTokenServices.revokeToken(access_token)){
            //return BaseResult.success("注销成功");
            return BaseResultUtil.success("注销成功");
            //return "注销成功";
        }else{
            //return BaseResult.failure("注销失败");
            return BaseResultUtil.failure(400,"注销失败");
            //return  "注销失败";
        }
    }
}
