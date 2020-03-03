package com.github.chenhao96.service;

import com.github.chenhao96.entity.bo.ATRoleBo;
import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.po.ATRoles;

import java.util.List;

public interface ATRoleService {

    boolean roleStatusChange(Integer roleId, Boolean status);

    boolean deleteRole(Integer roleId);

    List<ATRoles> pageQuery(PageQuery query);

    boolean saveRoleInfo(ATRoleBo atRoleBo);

    boolean addNewRole(ATRoleBo atRoleBo);

    ATRoles queryRoleById(Integer roleId);
}
