package com.github.chenhao96.service;

import com.github.chenhao96.entity.bo.ATControlBo;
import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.po.ATControls;

import java.util.List;

public interface ATControlService {

    ATControls queryControlById(Integer controlId);

    boolean addNewControl(ATControlBo bo);

    boolean saveControlInfo(ATControlBo bo);

    List<ATControls> pageQuery(PageQuery query);

    boolean deleteControl(Integer controlId);

    boolean controlStatusChange(Integer id, Boolean status);
}
