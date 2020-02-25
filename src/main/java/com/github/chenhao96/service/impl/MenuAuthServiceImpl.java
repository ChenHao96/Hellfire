package com.github.chenhao96.service.impl;

import com.github.chenhao96.adaptor.ATMenuAdaptor;
import com.github.chenhao96.adaptor.ATMenuAuthAdaptor;
import com.github.chenhao96.entity.po.ATMenuAuth;
import com.github.chenhao96.entity.po.ATMenus;
import com.github.chenhao96.entity.vo.AuthMenusTree;
import com.github.chenhao96.service.MenuAuthService;
import com.github.chenhao96.service.RoleAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.Future;

@Slf4j
@Service
public class MenuAuthServiceImpl implements MenuAuthService {

    @Resource
    private ATMenuAdaptor atMenuAdaptor;

    @Resource
    private ATMenuAuthAdaptor atMenuAuthAdaptor;

    @Resource
    private RoleAuthService roleAuthService;

    @Override
    public List<ATMenus> findMenusByUserId(Integer userId) {
        if (userId == null || userId < 1) return null;
        List<Integer> roleId = roleAuthService.findUserRoleAuth(userId);
        if (CollectionUtils.isEmpty(roleId)) return null;
        List<ATMenuAuth> menuAuth = atMenuAuthAdaptor.findMenusByRoleId(roleId);
        if (CollectionUtils.isEmpty(menuAuth)) return null;
        List<Integer> menuId = new ArrayList<>(menuAuth.size());
        for (ATMenuAuth auth : menuAuth) {
            if (ObjectUtils.nullSafeEquals(1, auth.getStatus())) {
                menuId.add(auth.getMenuId());
            }
        }
        return atMenuAdaptor.findMenusByIds(menuId);
    }

    @Async
    @Override
    public Future<List<AuthMenusTree>> queryMenuTreeByUser(Integer userId) {
        return new AsyncResult<>(findMenuTreeByUserId(findMenusByUserId(userId)));
    }

    private List<AuthMenusTree> findMenuTreeByUserId(List<ATMenus> menus) {
        List<AuthMenusTree> result = Collections.emptyList();
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
