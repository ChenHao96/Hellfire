package com.github.chenhao96.adaptor.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.chenhao96.adaptor.ATMenuAdaptor;
import com.github.chenhao96.entity.po.ATMenus;
import com.github.chenhao96.mapper.ATMenusMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ATMenuAdaptorImpl implements ATMenuAdaptor {

    @Resource
    private ATMenusMapper atMenusMapper;

    @Override
    public List<ATMenus> findMenusByIds(List<Integer> ids) {
        QueryWrapper<ATMenus> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        queryWrapper.orderByAsc("menu_no");
        return atMenusMapper.selectList(queryWrapper);
    }
}
