package com.github.chenhao96.config;

import com.github.chenhao96.security.SecurityAuthenticationHandler;
import com.github.chenhao96.security.SecuritySessionInformationExpiredStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.profiles.active}")
    private String active;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityAuthenticationHandler securityAuthenticationHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SessionInformationExpiredStrategy securitySessionInformationExpiredStrategy() {
        return new SecuritySessionInformationExpiredStrategy();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    public static final String LOGIN_URL_VALUE = "/login";
    public static final String ERROR_URL_VALUE = "/error";
    public static final String LOGOUT_URL_VALUE = "/logout";
    public static final String[] IGNORE_PATTERNS = new String[]{LOGIN_URL_VALUE, LOGOUT_URL_VALUE, ERROR_URL_VALUE, "/favicon.ico", "/css/**", "/js/**", "/image/**", "/framework/**"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests().antMatchers(IGNORE_PATTERNS).permitAll()
                .anyRequest().authenticated().and()
                .exceptionHandling().accessDeniedPage(ERROR_URL_VALUE);

        http.formLogin().loginPage(LOGIN_URL_VALUE)
                .failureHandler(securityAuthenticationHandler)
                .and().logout().logoutUrl(LOGOUT_URL_VALUE)
                .logoutSuccessUrl(LOGIN_URL_VALUE)
                .deleteCookies("JSESSIONID", "SESSIONID", "SESSION")
                .invalidateHttpSession(true);

        //最多运行一个账号登录
        //UserDetails需要实现hash,equals
        http.sessionManagement().maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(securitySessionInformationExpiredStrategy());
    }
}
