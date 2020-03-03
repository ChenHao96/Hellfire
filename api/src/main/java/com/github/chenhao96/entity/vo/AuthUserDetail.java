package com.github.chenhao96.entity.vo;

import com.github.chenhao96.entity.enums.UserStatusEnum;
import com.github.chenhao96.entity.po.ATUsers;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
@Accessors(chain = true)
public class AuthUserDetail extends ATUsers implements UserDetails {

    private List<AuthMenusTree> menuTrees;

    private List<AuthUrlControls> authorities;

    private LoginUserVo info;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UsersLogin{");
        sb.append("id=").append(getId());
        sb.append(", username='").append(getUsername()).append('\'');
        sb.append(", nickName='").append(getNickName()).append('\'');
        if(!StringUtils.isEmpty(getEmail()))
            sb.append(", email='").append(getEmail()).append('\'');
        sb.append(", phoneNumber='").append(getPhoneNumber()).append('\'');
        if (getPassword() != null)
            sb.append(", password= [PROTECTED]");
        if (getOptionPassword() != null)
            sb.append(", optionPassword= [PROTECTED]");
        sb.append(", status=").append(getStatus());
        sb.append(", createAt=").append(getCreateAt());
        sb.append(", createTime=").append(getCreateTime());
        if (getExpiredTime() != null)
            sb.append(", expiredTime=").append(getExpiredTime());
        sb.append(", logic=").append(getLogic());
        sb.append(", menuTrees=").append(menuTrees);
        sb.append(", authorities=").append(authorities);
        sb.append(", info=").append(info);
        sb.append('}');
        return sb.toString();
    }
}
