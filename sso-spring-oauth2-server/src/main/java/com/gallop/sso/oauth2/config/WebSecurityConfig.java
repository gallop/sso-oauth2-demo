package com.gallop.sso.oauth2.config;

import com.gallop.sso.oauth2.security.CustomAuthenticationFailureHandler;
import com.gallop.sso.oauth2.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * author gallop
 * date 2021-06-24 15:39
 * Description:
 * Modified By:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        //super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**", "/css/**", "/images/**");
        //super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests()
            // 自定义页面的路径不用验证
                .antMatchers(HttpMethod.GET, "/login").permitAll()
                //.antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")

                // 配置使用自定义失败处理器,如果配置failureUrl，则配置处理器无效
                .failureHandler(new CustomAuthenticationFailureHandler())
                //.failureUrl("/login?error=true")
                //.failureForwardUrl("/login?error=true")
                .and()
                .logout()
                // 退出登录的url, 默认为/logout
                .logoutSuccessUrl("/login")
                // 登录页表单提交的 action（th:action="@{/my-login}"） URL
                //.loginProcessingUrl("/my-login") 不设置，默认为 /login
                .and().csrf().disable().cors();

    }

    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
}
