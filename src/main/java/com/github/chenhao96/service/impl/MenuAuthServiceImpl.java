package com.github.chenhao96.service.impl;

import com.github.chenhao96.entity.vo.MenusTree;
import com.github.chenhao96.entity.vo.UsersLogin;
import com.github.chenhao96.service.MenuAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class MenuAuthServiceImpl implements MenuAuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuAuthServiceImpl.class);

    public List<MenusTree> findMenuTreeByUserId(Integer userId) {
        //TODO:
        return null;
    }

    @Async
    @Override
    public Future<List<MenusTree>> queryMenuTreeByUserId(Integer userId) {
        return new AsyncResult<>(findMenuTreeByUserId(userId));
    }

    @Async
    @Override
    public Future<Boolean> putMenuTreeByUser(UsersLogin user) {
        user.setMenuTrees(findMenuTreeByUserId(user.getId()));
        return new AsyncResult<>(true);
    }
}
