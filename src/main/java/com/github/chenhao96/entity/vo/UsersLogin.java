package com.github.chenhao96.entity.vo;

import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.entity.po.UserStatusEnum;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UsersLogin extends ATUsers implements UserDetails {

    private List<AuthMenusTree> menuTrees;

    private Collection<AuthUrlControls> authorities;

    @Override
    public Collection<AuthUrlControls> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Collection<AuthUrlControls> authorities) {
        this.authorities = authorities;
    }

    public List<AuthMenusTree> getMenuTrees() {
        return this.menuTrees;
    }

    public void setMenuTrees(List<AuthMenusTree> menuTrees) {
        this.menuTrees = menuTrees;
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
}
