package com.github.chenhao96.service.impl;

import com.github.chenhao96.entity.bo.ATRoleBo;
import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.po.ATRoles;
import com.github.chenhao96.entity.vo.PageResult;
import com.github.chenhao96.service.ATRoleService;
import org.springframework.stereotype.Service;

@Service
public class ATRoleServiceImpl implements ATRoleService {
    //TODO:
    @Override
    public boolean roleStatusChange(Integer roleId, Boolean status) {
        return false;
    }

    @Override
    public boolean deleteRole(Integer roleId) {
        return false;
    }

    @Override
    public PageResult<ATRoles> pageQuery(PageQuery query) {
        return null;
    }

    @Override
    public boolean saveRoleInfo(ATRoleBo atRoleBo) {
        return false;
    }

    @Override
    public boolean addNewRole(ATRoleBo atRoleBo) {
        return false;
    }

    @Override
    public ATRoles queryRoleById(Integer roleId) {
        return null;
    }
}
