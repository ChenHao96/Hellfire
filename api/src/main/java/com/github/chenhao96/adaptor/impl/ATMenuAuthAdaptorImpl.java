package com.github.chenhao96.adaptor.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.chenhao96.adaptor.ATMenuAuthAdaptor;
import com.github.chenhao96.entity.po.ATMenuAuth;
import com.github.chenhao96.mapper.ATMenuAuthMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ATMenuAuthAdaptorImpl implements ATMenuAuthAdaptor {

    @Resource
    private ATMenuAuthMapper atMenuAuthMapper;

    @Override
    public List<ATMenuAuth> findMenusByRoleId(List<Integer> roleId) {
        QueryWrapper<ATMenuAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleId);
        return atMenuAuthMapper.selectList(queryWrapper);
    }
}
