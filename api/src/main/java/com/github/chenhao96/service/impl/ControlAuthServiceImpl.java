package com.github.chenhao96.service.impl;

import com.github.chenhao96.adaptor.ATControlAdaptor;
import com.github.chenhao96.adaptor.ATControlAuthAdaptor;
import com.github.chenhao96.entity.po.ATControlAuth;
import com.github.chenhao96.entity.po.ATControls;
import com.github.chenhao96.entity.vo.AuthUrlControls;
import com.github.chenhao96.service.ControlAuthService;
import com.github.chenhao96.service.RoleAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class ControlAuthServiceImpl implements ControlAuthService {

    @Resource
    private ATControlAdaptor atControlAdaptor;

    @Resource
    private ATControlAuthAdaptor atControlAuthAdaptor;

    @Resource
    private RoleAuthService roleAuthService;

    @Override
    public List<ATControls> findControlsByUserId(Integer userId) {
        if (userId == null || userId < 1) return null;
        List<Integer> roleId = roleAuthService.findUserRoleAuth(userId);
        if (CollectionUtils.isEmpty(roleId)) return null;
        List<ATControlAuth> controlAuth = atControlAuthAdaptor.findControlsByRoleId(roleId);
        if (CollectionUtils.isEmpty(controlAuth)) return null;
        List<Integer> controlId = new ArrayList<>(controlAuth.size());
        for (ATControlAuth auth : controlAuth) {
            if (ObjectUtils.nullSafeEquals(true, auth.getStatus())) {
                controlId.add(auth.getControlId());
            }
        }
        return atControlAdaptor.findControlsByIds(controlId);
    }

    @Override
    public List<AuthUrlControls> queryAuthoritiesByUser(Integer userId) {
        return createAuthorities(findControlsByUserId(userId));
    }

    private List<AuthUrlControls> createAuthorities(List<ATControls> controls) {
        List<AuthUrlControls> result = Collections.emptyList();
        if (!CollectionUtils.isEmpty(controls)) {
            result = new LinkedList<>();
            for (ATControls control : controls) {
                if (ObjectUtils.nullSafeEquals(true, control.getStatus())) {
                    AuthUrlControls authUrlControl = new AuthUrlControls();
                    BeanUtils.copyProperties(control, authUrlControl);
                    result.add(authUrlControl);
                }
            }
        }
        return result;
    }
}
