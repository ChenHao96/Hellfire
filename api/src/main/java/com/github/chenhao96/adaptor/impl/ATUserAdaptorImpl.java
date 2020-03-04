package com.github.chenhao96.adaptor.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.chenhao96.adaptor.ATUserAdaptor;
import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.mapper.ATUsersMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Repository
public class ATUserAdaptorImpl extends BaseAdaptorImpl<ATUsers> implements ATUserAdaptor {

    @Resource
    private ATUsersMapper atUsersMapper;

    @Override
    protected BaseMapper<ATUsers> getBaseMapper() {
        return atUsersMapper;
    }

    @Override
    public ATUsers queryUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) return null;
        QueryWrapper<ATUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return atUsersMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean queryUsernameNotExist(String username) {
        if (StringUtils.isEmpty(username)) return true;
        QueryWrapper<ATUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id");
        queryWrapper.eq("username", username);
        return atUsersMapper.selectCount(queryWrapper) <= 0;
    }
}
