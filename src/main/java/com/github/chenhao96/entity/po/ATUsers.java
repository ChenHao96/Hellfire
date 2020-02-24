package com.github.chenhao96.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.chenhao96.entity.enums.UserStatusEnum;

import java.util.Date;

@TableName("a_t_users")
public class ATUsers extends BaseLogicTable {

    private String username;

    private String nickName;

    private String email;

    private String phoneNumber;

    private String password;

    private String optionPassword;

    private UserStatusEnum status;

    private Date createTime;

    private Date expiredTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatusEnum getStatus() {
        return status;
    }

    public void setStatus(UserStatusEnum status) {
        this.status = status;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOptionPassword() {
        return optionPassword;
    }

    public void setOptionPassword(String optionPassword) {
        this.optionPassword = optionPassword;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ATUsers{");
        sb.append("id=").append(getId());
        sb.append(", username='").append(username).append('\'');
        sb.append(", nickName='").append(nickName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", password= [PROTECTED]");
        sb.append(", optionPassword= [PROTECTED]");
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", expiredTime=").append(expiredTime);
        sb.append(", logic=").append(getLogic());
        sb.append('}');
        return sb.toString();
    }
}
