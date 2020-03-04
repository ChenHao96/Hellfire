package com.github.chenhao96.adaptor.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.chenhao96.adaptor.ATMenuAdaptor;
import com.github.chenhao96.entity.po.ATMenus;
import com.github.chenhao96.mapper.ATMenusMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ATMenuAdaptorImpl extends BaseAdaptorImpl<ATMenus> implements ATMenuAdaptor {

    @Resource
    private ATMenusMapper atMenusMapper;

    @Override
    protected BaseMapper<ATMenus> getBaseMapper() {
        return atMenusMapper;
    }

    @Override
    public List<ATMenus> findMenusByIds(List<Integer> ids) {
        QueryWrapper<ATMenus> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        queryWrapper.orderByAsc("menu_no");
        return atMenusMapper.selectList(queryWrapper);
    }
}
