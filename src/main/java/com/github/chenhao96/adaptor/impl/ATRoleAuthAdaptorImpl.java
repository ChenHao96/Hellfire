package com.github.chenhao96.adaptor.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.chenhao96.adaptor.ATRoleAuthAdaptor;
import com.github.chenhao96.entity.po.ATRoleAuth;
import com.github.chenhao96.mapper.ATRoleAuthMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ATRoleAuthAdaptorImpl implements ATRoleAuthAdaptor {

    @Resource
    private ATRoleAuthMapper atRoleAuthMapper;

    @Override
    public List<ATRoleAuth> findRoleIdsByUserId(Integer userId) {
        QueryWrapper<ATRoleAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return atRoleAuthMapper.selectList(queryWrapper);
    }
}
