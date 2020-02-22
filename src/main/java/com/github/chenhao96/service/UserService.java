package com.github.chenhao96.service;

import com.github.chenhao96.entity.po.ATUsers;

public interface UserService {

    ATUsers queryUserByUsername(String username);
}
