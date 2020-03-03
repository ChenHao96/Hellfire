package com.github.chenhao96.service;

import com.github.chenhao96.entity.bo.ATMenuBo;
import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.po.ATMenus;

import java.util.List;

public interface ATMenuService {
    ATMenus queryMenuById(Integer menuId);

    List<ATMenus> pageQuery(PageQuery query);

    boolean deleteMenu(Integer menuId);

    boolean addNewMenu(ATMenuBo atMenuBo);

    boolean saveMenuInfo(ATMenuBo atMenuBo);

    boolean menuStatusChange(Integer id, Boolean status);
}
