package com.github.chenhao96.service.impl;

import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.entity.vo.UsersLogin;
import com.github.chenhao96.service.ControlAuthService;
import com.github.chenhao96.service.MenuAuthService;
import com.github.chenhao96.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MenuAuthService menuAuthService;

    @Autowired
    private ControlAuthService controlAuthService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ATUsers atUsers = userService.queryUserByUsername(username);
        if (atUsers == null) {
            throw new UsernameNotFoundException("用户账号不存在!");
        }

        UsersLogin result = new UsersLogin();
        BeanUtils.copyProperties(atUsers, result);
        menuAuthService.queryMenuTreeByUser(result);
        controlAuthService.queryControlListByUser(result);
        return result;
    }
}
