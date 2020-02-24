package com.github.chenhao96.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("a_t_menus")
public class ATMenus extends BaseLogicTable {

    private Integer parentId;

    private String menuIcon;

    private String menuName;

    private String menuIndex;

    private String menuNo;

    private Boolean status;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuIndex() {
        return menuIndex;
    }

    public void setMenuIndex(String menuIndex) {
        this.menuIndex = menuIndex;
    }

    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

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
