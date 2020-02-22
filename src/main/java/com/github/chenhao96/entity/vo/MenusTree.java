package com.github.chenhao96.entity.vo;

import com.github.chenhao96.entity.po.ATMenus;

import java.util.List;

public class MenusTree extends ATMenus {

    private List<MenusTree> child;

    public List<MenusTree> getChild() {
        return child;
    }

    public void setChild(List<MenusTree> child) {
        this.child = child;
    }
}
