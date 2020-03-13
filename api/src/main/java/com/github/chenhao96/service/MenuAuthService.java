package com.github.chenhao96.service;

import com.github.chenhao96.entity.po.ATMenus;
import com.github.chenhao96.entity.vo.AuthMenusTree;

import java.util.List;

public interface MenuAuthService {

    List<ATMenus> findMenusByUserId(Integer userId);

    List<AuthMenusTree> queryMenuTreeByUser(Integer userId);
}
