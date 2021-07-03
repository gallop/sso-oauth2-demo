package com.gallop.sso.oauth2.service;

import com.gallop.sso.oauth2.entity.SysUser;

/**
 * author gallop
 * date 2021-06-24 16:01
 * Description:
 * Modified By:
 */
public interface UserService {
    SysUser getByUsername(String username);
}
