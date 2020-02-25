package com.github.chenhao96.service;

import com.github.chenhao96.entity.po.ATMenus;
import com.github.chenhao96.entity.vo.AuthMenusTree;

import java.util.List;
import java.util.concurrent.Future;

public interface MenuAuthService {

    List<ATMenus> findMenusByUserId(Integer userId);

    Future<List<AuthMenusTree>> queryMenuTreeByUser(Integer userId);
}
