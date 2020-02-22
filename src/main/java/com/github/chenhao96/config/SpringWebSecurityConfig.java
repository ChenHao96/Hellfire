package com.github.chenhao96.config;

import com.github.chenhao96.handler.SecurityAuthenticationHandler;
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    private static final String[] RESOURCES_PATTERNS = new String[]{"/login", "/css/**", "/js/**", "/image/**", "/framework/**", "/favicon.ico"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (!"prod".equalsIgnoreCase(active)) {
            //非生产环境时，允许跨域请求伪造
            http.csrf().disable();
        }

        //只能允许一个账号登录，如有其他设备登录，当前强制下线
        //http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
        //TODO:踢出登录未提示

        http.authorizeRequests().antMatchers(RESOURCES_PATTERNS).permitAll()
                .anyRequest().authenticated().and()
                .exceptionHandling().accessDeniedPage("/403");

        http.formLogin().loginPage("/login")
                .failureHandler(securityAuthenticationHandler)
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }
}
