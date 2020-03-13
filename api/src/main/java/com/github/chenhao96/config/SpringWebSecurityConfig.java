package com.github.chenhao96.config;

import com.github.chenhao96.controller.security.SecurityAccessDeniedHandler;
import com.github.chenhao96.controller.security.SecurityAuthenticationHandler;
import com.github.chenhao96.controller.security.SecurityLogoutSuccessHandler;
import com.github.chenhao96.controller.security.SecuritySessionInformationExpiredStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.annotation.Resource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_URL_VALUE = "/login";
    private static final String LOGOUT_URL_VALUE = "/logout";

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private SecurityAuthenticationHandler securityAuthenticationHandler;

    @Resource
    private SecurityLogoutSuccessHandler securityLogoutSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SessionInformationExpiredStrategy securitySessionInformationExpiredStrategy() {
        return new SecuritySessionInformationExpiredStrategy();
    }

    @Bean
    public AccessDeniedHandler securityAccessDeniedHandler() {
        return new SecurityAccessDeniedHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers(LOGIN_URL_VALUE, LOGOUT_URL_VALUE, "/favicon.ico")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(securityAccessDeniedHandler());

        http.formLogin()
                .failureHandler(securityAuthenticationHandler)
                .successHandler(securityAuthenticationHandler)
                .and()
                .logout()
                .logoutSuccessHandler(securityLogoutSuccessHandler)
                .deleteCookies("JSESSIONID", "SESSIONID", "SESSION")
                .clearAuthentication(true)
                .invalidateHttpSession(true);

        //最多一个账号登录
        //UserDetails需要实现hash,equals
        http.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(securitySessionInformationExpiredStrategy());
    }
}
