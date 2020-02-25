package com.github.chenhao96.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("a_t_menus")
public class ATMenus extends BaseLogicTable {

    private Integer parentId;

    private String menuIcon;

    private String menuName;

    private String menuIndex;

    private String menuNo;

    private Boolean status;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ATMenus{");
        sb.append("id=").append(getId());
        sb.append(", parentId=").append(parentId);
        sb.append(", menuIcon='").append(menuIcon).append('\'');
        sb.append(", menuName='").append(menuName).append('\'');
        sb.append(", menuIndex='").append(menuIndex).append('\'');
        sb.append(", menuNo='").append(menuNo).append('\'');
        sb.append(", status=").append(status);
        sb.append(", logic=").append(getLogic());
        sb.append('}');
        return sb.toString();
    }
}
