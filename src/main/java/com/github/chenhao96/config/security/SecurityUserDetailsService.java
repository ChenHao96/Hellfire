package com.github.chenhao96.config.security;

import com.github.chenhao96.entity.enums.UserStatusEnum;
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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUserDetailsService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MenuAuthService menuAuthService;

    @Autowired
    private ControlAuthService controlAuthService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        long startTime = System.currentTimeMillis();
        ATUsers atUsers = userService.queryUserByUsername(username);
        if (atUsers == null) {
            throw new UsernameNotFoundException("用户账号不存在!");
        }

        UsersLogin result = new UsersLogin();
        Future<Boolean> treeFuture = menuAuthService.putMenuTreeByUser(result);
        Future<Boolean> listFuture = controlAuthService.putAuthoritiesByUser(result);
        BeanUtils.copyProperties(atUsers, result);

        try {
            //等待其他线程完成
            listFuture.get();
            treeFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.warn("exec future fail.", e);
            result.setStatus(UserStatusEnum.DISABLE);
        }

        LOGGER.info("loadUserByUsername use time:{} Millis.",System.currentTimeMillis()-startTime);
        return result;
    }
}
