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

    public static final String[] IGNORE_PATTERNS = new String[]{"/login", "/logout", "/favicon.ico", "/css/**", "/js/**", "/image/**", "/framework/**"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (!"prod".equalsIgnoreCase(active)) {
            //非生产环境时，允许跨域请求伪造
            http.csrf().disable();
        }

        http.authorizeRequests().antMatchers(IGNORE_PATTERNS).permitAll()
                .anyRequest().fullyAuthenticated().and()
                .exceptionHandling().accessDeniedPage("/403");

        http.formLogin().loginPage("/login")
                .failureHandler(securityAuthenticationHandler)
                .and().logout().invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "SESSIONID", "SESSION")
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }
}
