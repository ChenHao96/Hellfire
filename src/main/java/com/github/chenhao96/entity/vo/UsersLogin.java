package com.github.chenhao96.entity.vo;

import com.github.chenhao96.entity.enums.UserStatusEnum;
import com.github.chenhao96.entity.po.ATUsers;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Accessors(chain = true)
public class UsersLogin extends ATUsers implements UserDetails {

    private List<AuthMenusTree> menuTrees;

    private Collection<AuthUrlControls> authorities;

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

    @Override
    public int hashCode() {
        return getUsername().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && this.hashCode() == obj.hashCode();
    }
}
