package com.gallop.user.sso;

import com.gallop.common.base.BaseResult;
import com.gallop.common.base.BaseResultUtil;
import com.gallop.user.pojo.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * author gallop
 * date 2021-06-17 16:40
 * Description:
 * Modified By:
 */
@Slf4j
public abstract class SsoServiceBasic implements SsoService{

    @Override
    public BaseResult returnspot(User user, HttpServletRequest request, HttpServletResponse response) {

        log.info("user信息：{}",user);
        Map returnMap = new HashMap();
        String uuid = UUID.randomUUID().toString();
        returnMap.put("loginToken", uuid);

        return BaseResultUtil.success(returnMap);
    }

}
