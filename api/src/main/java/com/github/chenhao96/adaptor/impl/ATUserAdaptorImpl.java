package com.github.chenhao96.adaptor.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.chenhao96.adaptor.ATUserAdaptor;
import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.mapper.ATUsersMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Repository
public class ATUserAdaptorImpl implements ATUserAdaptor {

    @Resource
    private ATUsersMapper atUsersMapper;

    @Override
    public ATUsers queryUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) return null;
        QueryWrapper<ATUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return atUsersMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean queryUsernameExist(String username) {
        if (StringUtils.isEmpty(username)) return false;
        QueryWrapper<ATUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id");
        queryWrapper.eq("username", username);
        return atUsersMapper.selectCount(queryWrapper) > 0;
    }

    @Override
    public boolean saveNewAtUser(ATUsers record) {
        if (record == null) return false;
        return atUsersMapper.insert(record) == 1;
    }

    @Override
    public boolean updateAtUserInfo(ATUsers record) {
        if (record == null) return false;
        return atUsersMapper.updateById(record) == 1;
    }

    @Override
    public ATUsers queryUserAccountById(Integer userId) {
        if (userId == null || userId < 1) return null;
        return atUsersMapper.selectById(userId);
    }

    @Override
    public boolean deleteUserAccount(Integer userId) {
        if (userId == null || userId < 1) return false;
        return atUsersMapper.deleteById(userId) == 1;
    }
}
