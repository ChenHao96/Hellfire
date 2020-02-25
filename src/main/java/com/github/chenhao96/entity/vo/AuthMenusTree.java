package com.github.chenhao96.entity.vo;

import com.github.chenhao96.entity.po.ATMenus;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
@Accessors(chain = true)
public class AuthMenusTree extends ATMenus {

    private List<AuthMenusTree> child;
    private List<AuthUrlControls> controls;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthMenusTree{");
        sb.append("id=").append(getId());
        if(!StringUtils.isEmpty(getParentId()))
            sb.append(", parentId=").append(getParentId());
        if(!StringUtils.isEmpty(getMenuIcon()))
            sb.append(", menuIcon='").append(getMenuIcon()).append('\'');
        sb.append(", menuName='").append(getMenuName()).append('\'');
        sb.append(", menuIndex='").append(getMenuIndex()).append('\'');
        sb.append(", menuNo='").append(getMenuNo()).append('\'');
        sb.append(", status=").append(getStatus());
        sb.append(", createAt=").append(getCreateAt());
        sb.append(", createTime=").append(getCreateTime());
        sb.append(", child=").append(child);
        sb.append(", controls=").append(controls);
        sb.append('}');
        return sb.toString();
    }
}
