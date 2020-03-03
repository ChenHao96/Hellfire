package com.github.chenhao96.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

@Data
@Accessors(chain = true)
@TableName("a_t_menus")
public class ATMenus extends BaseStatusTable {

    private Integer parentId;

    private String menuName;

    private String menuIndex;

    private String menuNo;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ATMenus{");
        sb.append("id=").append(getId());
        if(!StringUtils.isEmpty(parentId))
            sb.append(", parentId=").append(parentId);
        sb.append(", menuName='").append(menuName).append('\'');
        sb.append(", menuIndex='").append(menuIndex).append('\'');
        sb.append(", menuNo='").append(menuNo).append('\'');
        sb.append(", status=").append(getStatus());
        sb.append(", createAt=").append(getCreateAt());
        sb.append(", createTime=").append(getCreateTime());
        sb.append('}');
        return sb.toString();
    }
}
