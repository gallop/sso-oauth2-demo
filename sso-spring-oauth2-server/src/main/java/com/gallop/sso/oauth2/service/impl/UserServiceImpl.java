package com.gallop.sso.oauth2.service.impl;

import com.gallop.sso.oauth2.entity.SysUser;
import com.gallop.sso.oauth2.repository.SysUserRepository;
import com.gallop.sso.oauth2.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * author gallop
 * date 2021-06-24 16:16
 * Description:
 * Modified By:
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private SysUserRepository sysUserRepository;

    @Override
    public SysUser getByUsername(String username) {
        /*SysUser user = new SysUser();
        user.setUsername("gallop");
        user.setPassword("123456");*/
        return sysUserRepository.findByUsername(username);
    }
}
