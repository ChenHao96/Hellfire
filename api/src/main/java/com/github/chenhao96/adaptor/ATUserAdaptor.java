package com.github.chenhao96.adaptor;

import com.github.chenhao96.entity.po.ATUsers;

public interface ATUserAdaptor extends BaseAdaptor<ATUsers> {

    ATUsers queryUserByUsername(String username);

    boolean queryUsernameNotExist(String username);
}
