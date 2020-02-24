package com.github.chenhao96.adaptor.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.chenhao96.adaptor.ATUserAdaptor;
import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.mapper.ATUsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ATUserAdaptorImpl implements ATUserAdaptor {

    @Autowired
    private ATUsersMapper atUsersMapper;

    @Override
    public ATUsers queryUserByUsername(String username) {
        Wrapper<ATUsers> queryWrapper = new QueryWrapper<>();
        //TODO:设置参数
        return atUsersMapper.selectOne(queryWrapper);
    }
}
