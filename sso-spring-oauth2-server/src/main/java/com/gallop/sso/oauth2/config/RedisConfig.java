package com.gallop.sso.oauth2.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * author gallop
 * date 2021-06-02 15:04
 * Description:
 * Modified By:
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;


    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        /*RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setConnectionFactory(lettuceConnectionFactory);
        return template;*/

        //???????????????
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance , ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        //om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //??????redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);//key?????????
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);//value?????????
        redisTemplate.setHashKeySerializer(stringSerializer);//Hash key?????????
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);//Hash value?????????
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
      * date @2021-06-02
      * Description: ??????????????????
      * return:
      **/
    @Bean(value = "lettuceConnectionFactory")
    public LettuceConnectionFactory getConnectionFactory(GenericObjectPoolConfig genericPoolConfig) {
        //????????????
        /*RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisProperties.getHost());
        configuration.setPort(redisProperties.getPort());
        configuration.setDatabase(redisProperties.getDatabase());
        configuration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration, getPoolConfig(genericPoolConfig));
        */
        //????????????
        RedisSentinelConfiguration configuration1 = new RedisSentinelConfiguration(redisProperties.getSentinel().getMaster(),new HashSet<>(redisProperties.getSentinel().getNodes()));
        System.err.println("pwd:"+redisProperties.getSentinel().getPassword());
        //??????????????????????????????????????????????????????????????????redis master ????????????????????????????????????
        configuration1.setPassword(redisProperties.getPassword());
        configuration1.setSentinelPassword(redisProperties.getSentinel().getPassword());


        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration1, getPoolConfig(genericPoolConfig));
        //factory.setShareNativeConnection(false);//??????????????????????????????????????????????????????????????????true???false???????????????????????????????????????
        return factory;
    }

    @Bean
    //@ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
    @Scope(value = "prototype")
    public GenericObjectPoolConfig genericPoolConfig() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(redisProperties.getLettuce().getPool().getMaxActive());
        config.setMaxWaitMillis(redisProperties.getLettuce().getPool().getMaxWait().toMillis());
        config.setMaxIdle(redisProperties.getLettuce().getPool().getMaxIdle());
        config.setMinIdle(redisProperties.getLettuce().getPool().getMinIdle());
        return config;
    }

     /**
      * ?????????????????????
      **/
    @Bean
    public LettucePoolingClientConfiguration getPoolConfig(GenericObjectPoolConfig genericPoolConfig) {
        System.err.println("getMaxTotal==="+genericPoolConfig.getMaxTotal());

        LettucePoolingClientConfiguration pool = LettucePoolingClientConfiguration.builder()
                .poolConfig(genericPoolConfig)
                .commandTimeout(redisProperties.getTimeout())
                .shutdownTimeout(redisProperties.getLettuce().getShutdownTimeout())
                //????????????????????????????????????????????????????????????????????????CP
                .readFrom(ReadFrom.REPLICA_PREFERRED)
                //.clientOptions(clusterClientOptions)
                .build();

        return pool;
    }
}
