package com.github.chenhao96.entity.po;

import org.springframework.security.core.GrantedAuthority;

public class ATControls implements GrantedAuthority {

    private Integer id;

    private Integer menuId;

    private String button_index;

    private String authorityName;

    private String authority;

    private String menuNo;

    private Boolean needPassword;

    private Boolean status;

    private Boolean warringPrompt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
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
}
