package com.github.chenhao96.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class LoginUserVo implements Serializable {

    private static long serialVersionUID = 4771960991292883954L;

    private String nickName;

    private List<LoginMenuVo> menuTrees;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginUserVo{");
        sb.append("nickName='").append(nickName).append('\'');
        sb.append(", menuTrees=").append(menuTrees);
        sb.append('}');
        return sb.toString();
    }
}
