package com.github.chenhao96.entity;

import com.github.chenhao96.entity.po.ATUser;
import com.github.chenhao96.entity.po.UserStatusEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserLogin extends ATUser implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        //返回的没有过期
        return !UserStatusEnum.DISABLE.equals(getStatus());
    }

    @Override
    public boolean isAccountNonLocked() {
        //返回的没有锁定
        return !UserStatusEnum.LOCKED.equals(getStatus());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        long currentTime = System.currentTimeMillis();
        //返回的是密码时效没有过期
        return getExpiredTime() == null || getExpiredTime().getTime() > currentTime;
    }

    @Override
    public boolean isEnabled() {
        return UserStatusEnum.ENABLE.equals(getStatus());
    }
}
