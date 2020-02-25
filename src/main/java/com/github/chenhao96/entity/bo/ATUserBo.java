package com.github.chenhao96.entity.bo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ATUserBo {

    private Integer id;

    private String username;

    private String nickName;

    private String email;

    private String phoneNumber;

    private String password;

    private String optionPassword;

    private int status;

    private Date expiredTime;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ATUserBo{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", nickName='").append(nickName).append('\'');
        if(!StringUtils.isEmpty(email))
            sb.append(", email='").append(email).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        if (password != null)
            sb.append(", password= [PROTECTED] ");
        if (optionPassword != null)
            sb.append(", optionPassword= [PROTECTED] ");
        sb.append(", status=").append(status);
        if (expiredTime != null)
            sb.append(", expiredTime=").append(expiredTime);
        sb.append('}');
        return sb.toString();
    }
}
