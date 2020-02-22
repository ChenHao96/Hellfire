package com.github.chenhao96.service;

import com.github.chenhao96.entity.vo.MenusTree;
import com.github.chenhao96.entity.vo.UsersLogin;

import java.util.List;
import java.util.concurrent.Future;

public interface MenuAuthService {

    Future<List<MenusTree>> queryMenuTreeByUser(UsersLogin login);
}
