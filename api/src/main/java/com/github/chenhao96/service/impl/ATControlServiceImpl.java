package com.github.chenhao96.service.impl;

import com.github.chenhao96.entity.bo.ATControlBo;
import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.po.ATControls;
import com.github.chenhao96.entity.vo.PageResult;
import com.github.chenhao96.service.ATControlService;
import org.springframework.stereotype.Service;

@Service
public class ATControlServiceImpl implements ATControlService {
    //TODO:
    @Override
    public ATControls queryControlById(Integer controlId) {
        return null;
    }

    @Override
    public boolean addNewControl(ATControlBo bo) {
        return false;
    }

    @Override
    public boolean saveControlInfo(ATControlBo bo) {
        return false;
    }

    @Override
    public PageResult<ATControls> pageQuery(PageQuery query) {
        return null;
    }

    @Override
    public boolean deleteControl(Integer controlId) {
        return false;
    }

    @Override
    public boolean controlStatusChange(Integer id, Boolean status) {
        return false;
    }
}
