package com.github.chenhao96.service.impl;

import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.entity.po.UserStatusEnum;
import com.github.chenhao96.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ATUsers queryUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) return null;
        //TODO:
        ATUsers result = new ATUsers();
        result.setId(1);
        result.setNickName("傻蛋");
        result.setUsername(username);
        result.setStatus(UserStatusEnum.ENABLE);
        String password = DigestUtils.md5DigestAsHex("123456+".getBytes());
        result.setPassword(passwordEncoder.encode(password));
        return result;
    }
}
