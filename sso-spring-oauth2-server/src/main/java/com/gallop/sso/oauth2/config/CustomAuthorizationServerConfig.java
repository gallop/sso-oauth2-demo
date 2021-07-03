package com.gallop.sso.oauth2.config;

import com.gallop.sso.oauth2.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

/**
 * author gallop
 * date 2021-06-25 10:50
 * Description:
 * Modified By:
 */
@Configuration
@EnableAuthorizationServer
public class CustomAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Resource
    private ClientsService clientsService;
    @Autowired
    private RedisConnectionFactory lettuceConnectionFactory;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
        //super.configure(security);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //内存的方式配置client 账号，可以做临时测试用
        /*clients.inMemory()
                .withClient("client_001")
                .secret(passwordEncoder().encode("123456")) //passwordEncoder.encode("123456")
                .authorizedGrantTypes("authorization_code","refresh_token")
                .scopes("all","todo:read","todo:write")
                .redirectUris("http://www.baidu.com")
                .accessTokenValiditySeconds(86400)
                .refreshTokenValiditySeconds(604800);*/

        clients.withClientDetails(clientId -> {
            final ClientDetails clientDetails = this.clientsService.getByClientId(clientId);
            if (clientDetails == null) {
                throw new ClientRegistrationException(String.format("%s is not registered.", clientId));
            }
            return clientDetails;
        });
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //配置 oauth2 token 缓存到redis 中
        endpoints.tokenStore(redisTokenStore());
        super.configure(endpoints);
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new BCryptPasswordEncoder();
    }
    @Bean
    public RedisTokenStore redisTokenStore(){
        return new RedisTokenStore(lettuceConnectionFactory);
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.err.println("pwd:"+passwordEncoder.encode("123456"));
    }
}
