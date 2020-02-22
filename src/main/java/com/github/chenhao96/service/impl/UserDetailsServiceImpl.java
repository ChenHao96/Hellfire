package com.github.chenhao96.service.impl;

import com.github.chenhao96.entity.UserLogin;
import com.github.chenhao96.entity.po.UserStatusEnum;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("登录用户名不能为空!");
        }
        String password = DigestUtils.md5Hex("123456");
        UserLogin result = new UserLogin();
        result.setUsername(username);
        result.setStatus(UserStatusEnum.ENABLE);
        if ("admin".equalsIgnoreCase(username)) {
            result.setStatus(UserStatusEnum.DISABLE);
        } else if ("user".equalsIgnoreCase(username)) {
            result.setStatus(UserStatusEnum.LOCKED);
        } else if ("name".equalsIgnoreCase(username)) {
            result.setExpiredTime(new Date(System.currentTimeMillis() - 1000));
        }
        result.setPassword(passwordEncoder.encode(password));
        return result;
    }
}
