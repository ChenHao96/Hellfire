package com.github.chenhao96.service;

import com.github.chenhao96.entity.bo.ATUserBo;
import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.enums.UserStatusEnum;
import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.entity.vo.PageResult;

public interface ATUserService {

    boolean registerNewUser(ATUserBo user);

    ATUsers queryUserAccountById(Integer userId);

    boolean saveUserAccount(ATUserBo atUserBo);

    PageResult<ATUsers> pageQuery(PageQuery query);

    boolean deleteUserAccount(Integer userId);

    boolean userAccountStatusChange(Integer userId, UserStatusEnum status);

    ATUsers queryByUsername(String username);
}
