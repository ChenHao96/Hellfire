package com.github.chenhao96.controller.security;

import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.entity.vo.*;
import com.github.chenhao96.service.ATUserService;
import com.github.chenhao96.service.ControlAuthService;
import com.github.chenhao96.service.MenuAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Resource
    private ATUserService atUserService;

    @Resource
    private MenuAuthService menuAuthService;

    @Resource
    private ControlAuthService controlAuthService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        long startTime = System.currentTimeMillis();
        ATUsers atUsers = atUserService.queryByUsername(username);
        if (atUsers == null) {
            throw new UsernameNotFoundException("用户账号不存在!");
        }
        AuthUserDetail result = new AuthUserDetail();
        BeanUtils.copyProperties(atUsers, result);
        result.setPassword(passwordEncoder.encode(result.getPassword()));
        //TODO：密码错误也会导致查询
        result.setAuthorities(controlAuthService.queryAuthoritiesByUser(atUsers.getId()));
        result.setMenuTrees(menuAuthService.queryMenuTreeByUser(atUsers.getId()));
        userAuthInfo(result);
        log.info("loadUserByUsername use time:{} Millis.", System.currentTimeMillis() - startTime);
        return result;
    }

    private void userAuthInfo(AuthUserDetail detail) {
        LoginUserVo result = new LoginUserVo();
        result.setNickName(detail.getNickName());
        detail.setInfo(result);
        List<AuthMenusTree> menuTrees = detail.getMenuTrees();
        if (!CollectionUtils.isEmpty(menuTrees)) {
            List<AuthUrlControls> authorities = detail.getAuthorities();
            Map<Integer, List<LoginControlVo>> cache = null;
            if (!CollectionUtils.isEmpty(authorities)) {
                cache = new HashMap<>(authorities.size());
                for (AuthUrlControls controls : authorities) {
                    if (controls == null || controls.getMenuId() == null) continue;
                    List<LoginControlVo> list = cache.get(controls.getMenuId());
                    if (CollectionUtils.isEmpty(list)) {
                        list = new LinkedList<>();
                        cache.put(controls.getMenuId(), list);
                    }
                    LoginControlVo vo = new LoginControlVo();
                    BeanUtils.copyProperties(controls, vo);
                    list.add(vo);
                }
            }
            List<LoginMenuVo> resultMenu = new ArrayList<>(menuTrees.size());
            menuControlTree(menuTrees, cache, resultMenu);
            result.setMenuTrees(resultMenu);
        }
    }

    private void menuControlTree(List<AuthMenusTree> menuTrees, Map<Integer, List<LoginControlVo>> cache, List<LoginMenuVo> resultMenu) {
        for (AuthMenusTree tree : menuTrees) {
            if (tree == null || tree.getId() == null) continue;
            LoginMenuVo vo = new LoginMenuVo();
            BeanUtils.copyProperties(tree, vo);
            vo.setControls(Collections.emptyList());
            if (!CollectionUtils.isEmpty(cache))
                vo.setControls(cache.get(tree.getId()));
            resultMenu.add(vo);
            if (!CollectionUtils.isEmpty(tree.getChild())) {
                List<LoginMenuVo> childResultMenu = new ArrayList<>(tree.getChild().size());
                menuControlTree(tree.getChild(), cache, childResultMenu);
                vo.setChild(childResultMenu);
            }
        }
    }
}
