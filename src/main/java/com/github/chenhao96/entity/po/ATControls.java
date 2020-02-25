package com.github.chenhao96.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("a_t_controls")
public class ATControls extends BaseLogicTable {

    private Integer menuId;

    private String button_index;

    private String optionName;

    private String optionTag;

    private String menuNo;

    private Boolean needPassword;

    private Boolean status;

    private Boolean warringPrompt;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ATControls{");
        sb.append("id=").append(getId());
        sb.append(", menuId=").append(menuId);
        sb.append(", button_index='").append(button_index).append('\'');
        sb.append(", optionName='").append(optionName).append('\'');
        sb.append(", optionTag='").append(optionTag).append('\'');
        sb.append(", menuNo='").append(menuNo).append('\'');
        sb.append(", needPassword=").append(needPassword);
        sb.append(", status=").append(status);
        sb.append(", warringPrompt=").append(warringPrompt);
        sb.append(", logic=").append(getLogic());
        sb.append('}');
        return sb.toString();
    }
}
