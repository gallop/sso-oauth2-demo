package com.gallop.user.sso;

import java.util.HashMap;
import java.util.Map;

/**
 * author gallop
 * date 2021-06-17 16:53
 * Description:
 * Modified By:
 */
//@Component
public class SsoServiceFactory {
    private static final Map<String,SsoService> map = new HashMap<>();

    public static void register(SsoTypeEnum type,SsoService ssoService) throws Exception {

        if (type==null){
            throw new Exception("type不能为空");
        }
        map.put(type.getValue(), ssoService);
    }

    public static SsoService getSsoService(SsoTypeEnum type){
        return map.get(type.getValue());
    }
}
