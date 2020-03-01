package com.github.chenhao96.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("a_t_roles")
public class ATRoles extends BaseStatusTable {

    private String roleName;

    private String roleTag;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ATRoles{");
        sb.append("id=").append(getId());
        sb.append(", roleName='").append(roleName).append('\'');
        sb.append(", roleTag='").append(roleTag).append('\'');
        sb.append(", status=").append(getStatus());
        sb.append(", createAt=").append(getCreateAt());
        sb.append(", createTime=").append(getCreateTime());
        sb.append('}');
        return sb.toString();
    }
}
