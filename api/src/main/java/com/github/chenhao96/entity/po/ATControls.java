package com.github.chenhao96.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("a_t_controls")
public class ATControls extends BaseStatusTable {

    private Integer menuId;

    private String button_index;

    private String optionName;

    private String optionTag;

    private String menuNo;

    private Boolean needPassword;

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
        sb.append(", warringPrompt=").append(warringPrompt);
        sb.append(", status=").append(getStatus());
        sb.append(", createAt=").append(getCreateAt());
        sb.append(", createTime=").append(getCreateTime());
        sb.append('}');
        return sb.toString();
    }
}
