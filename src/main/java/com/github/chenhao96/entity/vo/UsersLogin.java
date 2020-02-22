package com.github.chenhao96.entity.vo;

import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.entity.po.UserStatusEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UsersLogin extends ATUsers implements UserDetails {

    private List<MenusTree> menuTrees;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
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

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public List<MenusTree> getMenuTrees() {
        return menuTrees;
    }

    public void setMenuTrees(List<MenusTree> menuTrees) {
        this.menuTrees = menuTrees;
    }
}
