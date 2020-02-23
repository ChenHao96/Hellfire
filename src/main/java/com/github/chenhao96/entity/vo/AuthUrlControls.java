package com.github.chenhao96.entity.vo;

import com.github.chenhao96.entity.po.ATControls;
import org.springframework.security.core.GrantedAuthority;

public class AuthUrlControls extends ATControls implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return getOptionTag();
    }
}
