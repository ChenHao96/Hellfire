package com.github.chenhao96.entity.vo;

import com.github.chenhao96.entity.po.ATControls;
import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.entity.po.UserStatusEnum;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UsersLogin extends ATUsers implements UserDetails {

    private List<MenusTree> menuTrees;

    private Collection<ATControls> authorities;

    @Override
    public Collection<ATControls> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !UserStatusEnum.DISABLE.equals(getStatus());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !UserStatusEnum.LOCKED.equals(getStatus());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        long currentTime = System.currentTimeMillis();
        return getExpiredTime() == null || getExpiredTime().getTime() > currentTime;
    }

    @Override
    public boolean isEnabled() {
        return UserStatusEnum.ENABLE.equals(getStatus());
    }

    public void setAuthorities(Collection<ATControls> authorities) {
        this.authorities = authorities;
    }

    public List<MenusTree> getMenuTrees() {
        return menuTrees;
    }

    public void setMenuTrees(List<MenusTree> menuTrees) {
        this.menuTrees = menuTrees;
    }
}
