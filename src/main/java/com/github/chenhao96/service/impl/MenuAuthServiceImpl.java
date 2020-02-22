package com.github.chenhao96.service.impl;

import com.github.chenhao96.entity.vo.MenusTree;
import com.github.chenhao96.entity.vo.UsersLogin;
import com.github.chenhao96.service.MenuAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class MenuAuthServiceImpl implements MenuAuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuAuthServiceImpl.class);

    @Async
    @Override
    public Future<List<MenusTree>> queryMenuTreeByUser(UsersLogin login) {
        //TODO:
        LOGGER.info("menu param login:{}", login);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
