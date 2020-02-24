package com.github.chenhao96.service.impl;

import com.github.chenhao96.entity.po.ATMenus;
import com.github.chenhao96.entity.vo.AuthMenusTree;
import com.github.chenhao96.entity.vo.UsersLogin;
import com.github.chenhao96.service.MenuAuthService;
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
public class MenuAuthServiceImpl implements MenuAuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuAuthServiceImpl.class);

    @Override
    public List<ATMenus> findMenusByUserId(Integer userId) {
        //TODO:
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Async
    @Override
    public Future<List<ATMenus>> queryMenusByUserId(Integer userId) {
        return new AsyncResult<>(findMenusByUserId(userId));
    }

    @Async
    @Override
    public Future<Boolean> putMenuTreeByUser(UsersLogin user) {
        user.setMenuTrees(findMenuTreeByUserId(user.getId()));
        return new AsyncResult<>(true);
    }

    private List<AuthMenusTree> findMenuTreeByUserId(Integer userId) {
        List<AuthMenusTree> result = Collections.emptyList();
        List<ATMenus> menus = findMenusByUserId(userId);
        if (!CollectionUtils.isEmpty(menus)) {
            Map<Integer, List<AuthMenusTree>> cache = new HashMap<>(menus.size());
            for (ATMenus menu : menus) {
                if (ObjectUtils.nullSafeEquals(true, menu.getStatus())) {
                    List<AuthMenusTree> parent = cache.computeIfAbsent(menu.getParentId(), k -> new LinkedList<>());
                    AuthMenusTree item = new AuthMenusTree();
                    BeanUtils.copyProperties(menu, item);
                    parent.add(item);
                }
            }
            if (!CollectionUtils.isEmpty(cache)) {
                result = findChildPutTree(null, cache);
                cache.clear();
            }
        }
        return result;
    }

    private List<AuthMenusTree> findChildPutTree(Integer parentId, Map<Integer, List<AuthMenusTree>> cache) {
        List<AuthMenusTree> currents = cache.get(parentId);
        if (!CollectionUtils.isEmpty(currents)) {
            for (AuthMenusTree menu : currents) {
                menu.setChild(findChildPutTree(menu.getId(), cache));
            }
            return currents;
        }
        return Collections.emptyList();
    }
}
