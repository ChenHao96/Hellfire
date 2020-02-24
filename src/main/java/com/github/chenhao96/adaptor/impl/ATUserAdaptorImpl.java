package com.github.chenhao96.adaptor.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.chenhao96.adaptor.ATUserAdaptor;
import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.mapper.ATUsersMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ATUserAdaptorImpl implements ATUserAdaptor {

    @Resource
    private ATUsersMapper atUsersMapper;

    @Override
    public ATUsers queryUserByUsername(String username) {
        QueryWrapper<ATUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return atUsersMapper.selectOne(queryWrapper);
    }
}
