package com.gallop.sso.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * author gallop
 * date 2021-06-28 15:15
 * Description: 配置spring session 存储于redis中
 * Modified By:
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
}
