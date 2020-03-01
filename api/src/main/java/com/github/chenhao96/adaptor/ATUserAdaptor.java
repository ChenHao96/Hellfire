package com.github.chenhao96.adaptor;

import com.github.chenhao96.entity.po.ATUsers;

public interface ATUserAdaptor {

    ATUsers queryUserByUsername(String username);

    boolean queryUsernameExist(String username);

    boolean saveNewAtUser(ATUsers record);
}