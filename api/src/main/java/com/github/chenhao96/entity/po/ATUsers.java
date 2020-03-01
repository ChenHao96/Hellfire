package com.github.chenhao96.entity.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.chenhao96.entity.enums.UserStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("a_t_users")
public class ATUsers extends BaseTable {

    private String username;

    private String nickName;

    private String email;

    private String phoneNumber;

    private String password;

    private String optionPassword;

    private UserStatusEnum status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expiredTime;

    @TableLogic(value = "0", delval = "1")
    private Boolean logic;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ATUsers{");
        sb.append("id=").append(getId());
        sb.append(", username='").append(username).append('\'');
        sb.append(", nickName='").append(nickName).append('\'');
        if(!StringUtils.isEmpty(email))
            sb.append(", email='").append(email).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        if (password != null)
            sb.append(", password= [PROTECTED]");
        if (optionPassword != null)
            sb.append(", optionPassword= [PROTECTED]");
        sb.append(", status=").append(status);
        sb.append(", createAt=").append(getCreateAt());
        sb.append(", createTime=").append(getCreateTime());
        if (expiredTime != null)
            sb.append(", expiredTime=").append(expiredTime);
        sb.append(", logic=").append(getLogic());
        sb.append('}');
        return sb.toString();
    }
}
