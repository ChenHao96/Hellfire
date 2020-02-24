package com.github.chenhao96.service.impl;

import com.github.chenhao96.adaptor.ATUserAdaptor;
import com.github.chenhao96.entity.bo.ATUserBo;
import com.github.chenhao96.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ATUserAdaptor atUserAdaptor;

    @Override
    public boolean registerNewUser(ATUserBo user) {
        //TODO:
        return false;
    }
}
