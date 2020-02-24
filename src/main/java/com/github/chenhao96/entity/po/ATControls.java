package com.github.chenhao96.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;

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

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getButton_index() {
        return button_index;
    }

    public void setButton_index(String button_index) {
        this.button_index = button_index;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionTag() {
        return optionTag;
    }

    public void setOptionTag(String optionTag) {
        this.optionTag = optionTag;
    }

    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo;
    }

    public Boolean getNeedPassword() {
        return needPassword;
    }

    public void setNeedPassword(Boolean needPassword) {
        this.needPassword = needPassword;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getWarringPrompt() {
        return warringPrompt;
    }

    public void setWarringPrompt(Boolean warringPrompt) {
        this.warringPrompt = warringPrompt;
    }

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
