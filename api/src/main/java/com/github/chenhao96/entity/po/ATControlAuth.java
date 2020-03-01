package com.github.chenhao96.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("a_t_control_auth")
public class ATControlAuth extends BaseStatusTable {

    private Integer menuId;

    private Integer controlId;

    private Integer roleId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ATControlAuth{");
        sb.append("id=").append(getId());
        sb.append(", menuId=").append(menuId);
        sb.append(", controlId=").append(controlId);
        sb.append(", roleId=").append(roleId);
        sb.append(", status=").append(getStatus());
        sb.append(", createAt=").append(getCreateAt());
        sb.append(", createTime=").append(getCreateTime());
        sb.append('}');
        return sb.toString();
    }
}
