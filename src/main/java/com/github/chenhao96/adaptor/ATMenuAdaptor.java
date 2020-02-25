package com.github.chenhao96.adaptor;

import com.github.chenhao96.entity.po.ATMenus;

import java.util.List;

public interface ATMenuAdaptor {

    List<ATMenus> findMenusByIds(List<Integer> ids);
}
