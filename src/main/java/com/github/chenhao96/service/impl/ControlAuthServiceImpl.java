package com.github.chenhao96.service.impl;

import com.github.chenhao96.entity.po.ATControls;
import com.github.chenhao96.entity.vo.UsersLogin;
import com.github.chenhao96.service.ControlAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class ControlAuthServiceImpl implements ControlAuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControlAuthServiceImpl.class);

    @Override
    public List<ATControls> findControlsByUserId(Integer userId) {
        //TODO:
        return null;
    }

    @Async
    @Override
    public Future<List<ATControls>> queryControlsByUserId(Integer userId) {
        return new AsyncResult<>(findControlsByUserId(userId));
    }

    @Async
    @Override
    public Future<Boolean> putControlListByUser(UsersLogin user) {
        user.setAuthorities(findControlsByUserId(user.getId()));
        return new AsyncResult<>(true);
    }
}
