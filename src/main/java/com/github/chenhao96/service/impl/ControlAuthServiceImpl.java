package com.github.chenhao96.service.impl;

import com.github.chenhao96.entity.vo.UsersLogin;
import com.github.chenhao96.service.ControlAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.Future;

@Service
public class ControlAuthServiceImpl implements ControlAuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControlAuthServiceImpl.class);

    @Async
    @Override
    public Future<Collection<GrantedAuthority>> queryControlListByUser(UsersLogin login) {
        //TODO:
        LOGGER.info("control param login:{}", login);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
