package com.github.chenhao96.entity.bo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ATMenuBo {

    private Integer id;

    private String menuName;

    private String menuIndex;

    private String menuNo;

    private Boolean status;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ATMenuBo{");
        sb.append("id=").append(id);
        sb.append(", menuName='").append(menuName).append('\'');
        sb.append(", menuIndex='").append(menuIndex).append('\'');
        sb.append(", menuNo='").append(menuNo).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
