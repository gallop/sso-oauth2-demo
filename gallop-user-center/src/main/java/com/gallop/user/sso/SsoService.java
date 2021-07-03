package com.gallop.user.sso;

import com.gallop.common.base.BaseResult;
import com.gallop.user.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author gallop
 * date 2021-06-17 16:33
 * Description:
 * Modified By:
 */
public interface SsoService {

    SsoTypeEnum ssoType();
    BaseResult login(User user, HttpServletRequest request, HttpServletResponse response);
    BaseResult returnspot(User user, HttpServletRequest request, HttpServletResponse response);
}
