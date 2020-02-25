package com.github.chenhao96.adaptor.impl;

import com.github.chenhao96.adaptor.ATRoleAdaptor;
import com.github.chenhao96.mapper.ATRolesMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ATRoleAdaptorImpl implements ATRoleAdaptor {

    @Resource
    private ATRolesMapper atRolesMapper;
}
