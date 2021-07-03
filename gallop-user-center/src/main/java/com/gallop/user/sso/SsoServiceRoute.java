package com.gallop.user.sso;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * author gallop
 * date 2021-06-17 17:11
 * Description:
 * Modified By:
 */
@Service
public class SsoServiceRoute {
    @Resource
    private SsoService[] soServices;


    public SsoService route(SsoTypeEnum ssoType) throws Exception {
        for (SsoService ssoService : soServices) {
            if (ssoType == ssoService.ssoType()) {
                return ssoService;
            }
        }
        throw new Exception("没有可用的ssoService");
    }
}
