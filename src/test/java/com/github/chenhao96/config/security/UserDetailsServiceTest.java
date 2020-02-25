package com.github.chenhao96.config.security;

import com.github.chenhao96.BaseTest;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

public class UserDetailsServiceTest extends BaseTest {

    @Resource
    private UserDetailsService userDetailsService;

    @Test
    public void queryUserByUsername() {
        System.out.println(userDetailsService.loadUserByUsername("steven"));
    }
}
