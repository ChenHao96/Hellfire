package com.github.chenhao96.service.impl;

import com.github.chenhao96.entity.po.ATControls;
import com.github.chenhao96.entity.vo.AuthUrlControls;
import com.github.chenhao96.entity.vo.UsersLogin;
import com.github.chenhao96.service.ControlAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.concurrent.Future;

@Service
public class ControlAuthServiceImpl implements ControlAuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControlAuthServiceImpl.class);

    @Override
    public List<ATControls> findControlsByUserId(Integer userId) {
        //TODO:
        List<ATControls> result = new ArrayList<>(1);
        ATControls item = new ATControls();
        item.setOptionTag("commons:test");
        result.add(item);
        return result;
    }

    @Async
    @Override
    public Future<List<ATControls>> queryControlsByUserId(Integer userId) {
        return new AsyncResult<>(findControlsByUserId(userId));
    }

    private Collection<AuthUrlControls> createAuthorities(List<ATControls> controls) {
        List<AuthUrlControls> result = Collections.emptyList();
        if (!CollectionUtils.isEmpty(controls)) {
            result = new LinkedList<>();
            for (ATControls control : controls) {
                if (ObjectUtils.nullSafeEquals(control.getStatus(), true)) {
                    AuthUrlControls authUrlControl = new AuthUrlControls();
                    BeanUtils.copyProperties(control, authUrlControl);
                    result.add(authUrlControl);
                }
            }
        }
        return result;
    }

    @Async
    @Override
    public Future<Boolean> putAuthoritiesByUser(UsersLogin user) {
        user.setAuthorities(createAuthorities(findControlsByUserId(user.getId())));
        return new AsyncResult<>(true);
    }
}
