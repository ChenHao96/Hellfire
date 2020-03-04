package com.github.chenhao96.adaptor;

import com.github.chenhao96.entity.po.ATMenus;

import java.util.List;

public interface ATMenuAdaptor extends BaseAdaptor<ATMenus> {

    List<ATMenus> findMenusByIds(List<Integer> ids);
}
