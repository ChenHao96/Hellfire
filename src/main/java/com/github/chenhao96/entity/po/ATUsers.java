package com.github.chenhao96.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.chenhao96.entity.enums.UserStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("a_t_users")
public class ATUsers extends BaseLogicTable {

    private String username;

    private String nickName;

    private String email;

    private String phoneNumber;

    private String password;

    private String optionPassword;

    private UserStatusEnum status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expiredTime;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ATUsers{");
        sb.append("id=").append(getId());
        sb.append(", username='").append(username).append('\'');
        sb.append(", nickName='").append(nickName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        if (password != null)
            sb.append(", password= [PROTECTED]");
        if (optionPassword != null)
            sb.append(", optionPassword= [PROTECTED]");
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", expiredTime=").append(expiredTime);
        sb.append(", logic=").append(getLogic());
        sb.append('}');
        return sb.toString();
    }
}
