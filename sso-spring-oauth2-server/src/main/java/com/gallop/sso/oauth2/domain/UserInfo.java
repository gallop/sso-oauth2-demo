package com.gallop.sso.oauth2.domain;

import com.gallop.sso.oauth2.entity.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * author gallop
 * date 2021-07-01 14:03
 * Description:
 * Modified By:
 */

public class UserInfo extends User {
    private SysUser sysUser;

    public UserInfo(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
