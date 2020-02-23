package com.github.chenhao96.entity.vo;

import com.github.chenhao96.entity.po.ATMenus;

import java.util.List;

public class AuthMenusTree extends ATMenus {

    private List<AuthMenusTree> child;
    private List<AuthUrlControls> controls;

    public List<AuthMenusTree> getChild() {
        return child;
    }

    public void setChild(List<AuthMenusTree> child) {
        this.child = child;
    }

    public List<AuthUrlControls> getControls() {
        return controls;
    }

    public void setControls(List<AuthUrlControls> controls) {
        this.controls = controls;
    }
}
