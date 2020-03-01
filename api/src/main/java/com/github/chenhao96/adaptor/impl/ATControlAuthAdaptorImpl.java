package com.github.chenhao96.adaptor.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.chenhao96.adaptor.ATControlAuthAdaptor;
import com.github.chenhao96.entity.po.ATControlAuth;
import com.github.chenhao96.mapper.ATControlAuthMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ATControlAuthAdaptorImpl implements ATControlAuthAdaptor {

    @Resource
    private ATControlAuthMapper atControlAuthMapper;

    @Override
    public List<ATControlAuth> findControlsByRoleId(List<Integer> roleId) {
        QueryWrapper<ATControlAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleId);
        return atControlAuthMapper.selectList(queryWrapper);
    }
}
