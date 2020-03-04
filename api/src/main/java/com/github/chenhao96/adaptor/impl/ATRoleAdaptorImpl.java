package com.github.chenhao96.adaptor.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.chenhao96.adaptor.ATRoleAdaptor;
import com.github.chenhao96.entity.po.ATRoles;
import com.github.chenhao96.mapper.ATRolesMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ATRoleAdaptorImpl extends BaseAdaptorImpl<ATRoles> implements ATRoleAdaptor {

    @Resource
    private ATRolesMapper atRolesMapper;

    @Override
    protected BaseMapper<ATRoles> getBaseMapper() {
        return atRolesMapper;
    }
}
