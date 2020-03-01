package com.github.chenhao96.adaptor;

import com.github.chenhao96.entity.po.ATControlAuth;

import java.util.List;

public interface ATControlAuthAdaptor {

    List<ATControlAuth> findControlsByRoleId(List<Integer> roleId);
}
