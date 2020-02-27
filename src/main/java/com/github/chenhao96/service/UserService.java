package com.github.chenhao96.service;

import com.github.chenhao96.entity.bo.ATUserBo;
import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.entity.vo.PageResult;

import java.util.List;

public interface UserService {

    boolean registerNewUser(ATUserBo user);

    ATUsers queryUserAccountById(Integer userId);

    List<ATUsers> queryTopCountList(Integer pageCount);

    boolean saveUserAccount(ATUserBo atUserBo);

    PageResult<ATUsers> pageQuery(PageQuery query);
}
