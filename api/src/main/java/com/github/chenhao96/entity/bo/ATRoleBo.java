package com.github.chenhao96.entity.bo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ATRoleBo {

    private Integer id;

    private String roleName;

    private String roleTag;

    private Boolean status;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ATRoleBo{");
        sb.append("id=").append(id);
        sb.append(", roleName='").append(roleName).append('\'');
        sb.append(", roleTag='").append(roleTag).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
