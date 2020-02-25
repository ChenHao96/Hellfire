package com.github.chenhao96.entity.vo;

import com.github.chenhao96.entity.po.ATMenus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AuthMenusTree extends ATMenus {

    private List<AuthMenusTree> child;
    private List<AuthUrlControls> controls;
}
