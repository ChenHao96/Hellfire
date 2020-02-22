package com.github.chenhao96.service;

import com.github.chenhao96.entity.vo.UsersLogin;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.concurrent.Future;

public interface ControlAuthService {

    Future<Collection<GrantedAuthority>> queryControlListByUser(UsersLogin login);
}
