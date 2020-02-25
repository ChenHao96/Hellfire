package com.github.chenhao96.service.impl;

import com.github.chenhao96.adaptor.ATRoleAuthAdaptor;
import com.github.chenhao96.entity.po.ATRoleAuth;
import com.github.chenhao96.service.RoleAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RoleAuthServiceImpl implements RoleAuthService {

    @Resource
    private ATRoleAuthAdaptor atRoleAuthAdaptor;

    @Override
    public List<Integer> findUserRoleAuth(Integer userId) {
        if (userId == null || userId < 1) return null;
        List<ATRoleAuth> roleAuth = atRoleAuthAdaptor.findRoleIdsByUserId(userId);
        if (CollectionUtils.isEmpty(roleAuth)) return null;
        List<Integer> roleId = new ArrayList<>(roleAuth.size());
        for (ATRoleAuth auth : roleAuth) {
            if (ObjectUtils.nullSafeEquals(1, auth.getStatus())) {
                roleId.add(auth.getRoleId());
            }
        }
        return roleId;
    }
}
