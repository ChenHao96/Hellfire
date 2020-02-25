package com.github.chenhao96.config.security;

import com.github.chenhao96.adaptor.ATUserAdaptor;
import com.github.chenhao96.entity.enums.UserStatusEnum;
import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.entity.vo.AuthMenusTree;
import com.github.chenhao96.entity.vo.AuthUrlControls;
import com.github.chenhao96.entity.vo.UsersLogin;
import com.github.chenhao96.service.ControlAuthService;
import com.github.chenhao96.service.MenuAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUserDetailsService.class);

    @Resource
    private ATUserAdaptor userAdaptor;

    @Resource
    private MenuAuthService menuAuthService;

    @Resource
    private ControlAuthService controlAuthService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        long startTime = System.currentTimeMillis();
        ATUsers atUsers = userAdaptor.queryUserByUsername(username);
        if (atUsers == null) {
            throw new UsernameNotFoundException("用户账号不存在!");
        }

        UsersLogin result = new UsersLogin();
        Future<List<AuthMenusTree>> treeFuture = menuAuthService.queryMenuTreeByUser(atUsers.getId());
        Future<List<AuthUrlControls>> listFuture = controlAuthService.queryAuthoritiesByUser(atUsers.getId());
        BeanUtils.copyProperties(atUsers, result);
        result.setPassword(passwordEncoder.encode(result.getPassword()));
        
        try {
            //等待其他线程完成
            result.setAuthorities(listFuture.get());
            result.setMenuTrees(treeFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.warn("exec future fail.", e);
            result.setStatus(UserStatusEnum.DISABLE);
        }

        LOGGER.info("loadUserByUsername use time:{} Millis.", System.currentTimeMillis() - startTime);
        return result;
    }
}
