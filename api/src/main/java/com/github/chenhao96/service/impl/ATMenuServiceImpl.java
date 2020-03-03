package com.github.chenhao96.service.impl;

import com.github.chenhao96.entity.bo.ATMenuBo;
import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.po.ATMenus;
import com.github.chenhao96.service.ATMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ATMenuServiceImpl implements ATMenuService {
    @Override
    public ATMenus queryMenuById(Integer menuId) {
        return null;
    }

    @Override
    public List<ATMenus> pageQuery(PageQuery query) {
        return null;
    }

    @Override
    public boolean deleteMenu(Integer menuId) {
        return false;
    }

    @Override
    public boolean addNewMenu(ATMenuBo atMenuBo) {
        return false;
    }

    @Override
    public boolean saveMenuInfo(ATMenuBo atMenuBo) {
        return false;
    }

    @Override
    public boolean menuStatusChange(Integer id, Boolean status) {
        return false;
    }
}
