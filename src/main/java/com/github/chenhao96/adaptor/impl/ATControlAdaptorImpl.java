package com.github.chenhao96.adaptor.impl;

import com.github.chenhao96.adaptor.ATControlAdaptor;
import com.github.chenhao96.entity.po.ATControls;
import com.github.chenhao96.mapper.ATControlsMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ATControlAdaptorImpl implements ATControlAdaptor {

    @Resource
    private ATControlsMapper atControlsMapper;

    @Override
    public List<ATControls> findControlsByIds(List<Integer> ids) {
        return atControlsMapper.selectBatchIds(ids);
    }
}
