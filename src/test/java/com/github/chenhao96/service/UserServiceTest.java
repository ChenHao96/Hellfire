package com.github.chenhao96.service;

import com.github.chenhao96.BaseTest;
import com.github.chenhao96.entity.bo.ATUserBo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void testRegisterUser() {
        ATUserBo user = new ATUserBo();
        user.setStatus(1);
        user.setUsername("jacky");
        user.setNickName("傻蛋蛋");
        user.setPhoneNumber("13112345678");
        user.setPassword(DigestUtils.md5DigestAsHex("123456+".getBytes()));
        boolean success = userService.registerNewUser(user);
        Assert.isTrue(success, "新用户注册失败");
    }
}
