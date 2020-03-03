package com.github.chenhao96.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class LoginMenuVo implements Serializable {

    private static long serialVersionUID = 5571960708292883954L;

    private String menuName;

    private String menuIndex;

    private String menuNo;

    private List<LoginMenuVo> child;

    private List<LoginControlVo> controls;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginMenuVo{");
        sb.append("menuName='").append(menuName).append('\'');
        sb.append(", menuIndex='").append(menuIndex).append('\'');
        sb.append(", menuNo='").append(menuNo).append('\'');
        sb.append(", child=").append(child);
        sb.append(", controls=").append(controls);
        sb.append('}');
        return sb.toString();
    }
}
