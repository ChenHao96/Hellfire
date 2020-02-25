package com.github.chenhao96.adaptor;

import com.github.chenhao96.entity.po.ATRoleAuth;

import java.util.List;

public interface ATRoleAuthAdaptor {

    List<ATRoleAuth> findRoleIdsByUserId(Integer userId);
}
