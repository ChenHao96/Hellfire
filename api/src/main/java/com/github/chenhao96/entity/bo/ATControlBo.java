package com.github.chenhao96.entity.bo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ATControlBo {

    private Integer id;

    private Integer menuId;

    private String buttonIndex;

    private String optionName;

    private String optionTag;

    private Boolean needPassword;

    private Boolean warringPrompt;

    private Boolean status;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ATControlBo{");
        sb.append("id=").append(id);
        sb.append(", menuId=").append(menuId);
        sb.append(", buttonIndex='").append(buttonIndex).append('\'');
        sb.append(", optionName='").append(optionName).append('\'');
        sb.append(", optionTag='").append(optionTag).append('\'');
        sb.append(", needPassword=").append(needPassword);
        sb.append(", warringPrompt=").append(warringPrompt);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
