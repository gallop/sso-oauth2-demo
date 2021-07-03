package com.gallop.sso.oauth2;

import com.gallop.sso.oauth2.entity.Clients;
import com.gallop.sso.oauth2.entity.SysUser;
import com.gallop.sso.oauth2.repository.ClientsRepository;
import com.gallop.sso.oauth2.repository.SysUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.PublicKey;

@SpringBootTest
class SsoSpringOauth2ServerApplicationTests {
    @Autowired
    private ClientsRepository clientsRepository;
    @Autowired
    private SysUserRepository sysUserRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        System.err.println("pwd:"+passwordEncoder().encode("123456"));
    }

    private PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Test
    public void clientsTest(){
        Clients clients = clientsRepository.findById("client_001");
        System.err.println("client:"+clients.toString());
    }
    @Test
    public void sysUserTest(){
        SysUser sysUser = sysUserRepository.findByUsername("gallop");
        System.err.println("sysUser:"+sysUser.toString());
    }

}
