package com.github.chenhao96.adaptor.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.chenhao96.adaptor.ATControlAdaptor;
import com.github.chenhao96.entity.po.ATControls;
import com.github.chenhao96.mapper.ATControlsMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ATControlAdaptorImpl extends BaseAdaptorImpl<ATControls> implements ATControlAdaptor {

    @Resource
    private ATControlsMapper atControlsMapper;

    @Override
    protected BaseMapper<ATControls> getBaseMapper() {
        return atControlsMapper;
    }

    @Override
    public List<ATControls> findControlsByIds(List<Integer> ids) {
        return atControlsMapper.selectBatchIds(ids);
    }
}
