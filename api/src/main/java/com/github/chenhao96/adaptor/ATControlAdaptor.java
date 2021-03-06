package com.github.chenhao96.adaptor;

import com.github.chenhao96.entity.po.ATControls;

import java.util.List;

public interface ATControlAdaptor extends BaseAdaptor<ATControls> {

    List<ATControls> findControlsByIds(List<Integer> ids);
}
