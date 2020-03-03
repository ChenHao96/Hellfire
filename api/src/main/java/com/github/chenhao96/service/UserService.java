package com.github.chenhao96.service;

import com.github.chenhao96.entity.bo.ATUserBo;
import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.po.ATUsers;

import java.util.List;

public interface UserService {

    boolean registerNewUser(ATUserBo user);

    ATUsers queryUserAccountById(Integer userId);

    boolean saveUserAccount(ATUserBo atUserBo);

    List<ATUsers> pageQuery(PageQuery query);

    boolean deleteUserAccount(Integer userId);

    boolean userAccountStatusChange(Integer userId, Integer status);

    ATUsers queryByUsername(String username);
}
