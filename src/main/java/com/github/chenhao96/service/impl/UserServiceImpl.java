package com.github.chenhao96.service.impl;

import com.github.chenhao96.adaptor.ATUserAdaptor;
import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ATUserAdaptor atUserAdaptor;

    @Override
    public ATUsers queryUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) return null;
        return atUserAdaptor.queryUserByUsername(username);
    }
}
