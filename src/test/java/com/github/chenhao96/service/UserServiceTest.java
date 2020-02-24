package com.github.chenhao96.service;

import com.github.chenhao96.BaseTest;
import com.github.chenhao96.entity.po.ATUsers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void queryUserByUsername() {
        ATUsers user = userService.queryUserByUsername("admin");
        System.out.println(user);
    }
}
