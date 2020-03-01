package com.github.chenhao96.service;

import java.util.List;

public interface RoleAuthService {

    List<Integer> findUserRoleAuth(Integer userId);
}
