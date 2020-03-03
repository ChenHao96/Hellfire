package com.github.chenhao96.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class LoginControlVo implements Serializable {

    private static long serialVersionUID = 5571960991292883954L;

    private String buttonIndex;

    private String optionName;

    private String menuNo;

    private Boolean needPassword;

    private Boolean warringPrompt;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginControlVo{");
        sb.append("buttonIndex='").append(buttonIndex).append('\'');
        sb.append(", optionName='").append(optionName).append('\'');
        sb.append(", menuNo='").append(menuNo).append('\'');
        sb.append(", needPassword=").append(needPassword);
        sb.append(", warringPrompt=").append(warringPrompt);
        sb.append('}');
        return sb.toString();
    }
}
