package com.gallop.sso.oauth2.repository;

import com.gallop.sso.oauth2.entity.SysUser;
import org.springframework.data.repository.Repository;

/**
 * author gallop
 * date 2021-06-28 11:46
 * Description:
 * Modified By:
 */
public interface SysUserRepository extends Repository<SysUser,Integer> {

    SysUser findByUsername(String userName);
}
