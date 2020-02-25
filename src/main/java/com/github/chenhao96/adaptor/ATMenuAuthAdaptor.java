package com.github.chenhao96.adaptor;

import com.github.chenhao96.entity.po.ATMenuAuth;

import java.util.List;

public interface ATMenuAuthAdaptor {

    List<ATMenuAuth> findMenusByRoleId(List<Integer> roleId);
}
