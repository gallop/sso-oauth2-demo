package com.gallop.sso.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author gallop
 * date 2021-06-29 13:40
 * Description:
 * Modified By:
 */
//@Configuration
@Deprecated
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        List<MediaType> mediaTypes = Arrays.asList(
                MediaType.APPLICATION_JSON,
                MediaType.TEXT_PLAIN,
                MediaType.TEXT_HTML,
                MediaType.TEXT_XML,
                MediaType.APPLICATION_OCTET_STREAM);
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setSupportedMediaTypes(mediaTypes);
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        converters.add(stringConverter);
        converters.add(jsonConverter);
    }
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        //configurer.favorPathExtension(false);
        /* 是否通过请求Url的扩展名来决定media type *//*
        configurer.favorPathExtension(true)
                *//* 不检查Accept请求头 *//*
                .ignoreAcceptHeader(true)
                .parameterName("mediaType")
                *//* 设置默认的media yype *//*
                .defaultContentType(MediaType.TEXT_HTML)
                *//* 请求以.html结尾的会被当成MediaType.TEXT_HTML*//*
                .mediaType("html", MediaType.TEXT_HTML)
                *//* 请求以.json结尾的会被当成MediaType.APPLICATION_JSON*//*
                .mediaType("json", MediaType.APPLICATION_JSON);*/
    }
}
