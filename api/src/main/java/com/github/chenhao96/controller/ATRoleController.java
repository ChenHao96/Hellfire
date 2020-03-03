package com.github.chenhao96.controller;

import com.github.chenhao96.entity.bo.ATRoleBo;
import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.po.ATRoles;
import com.github.chenhao96.entity.vo.BaseResult;
import com.github.chenhao96.entity.vo.PageResult;
import com.github.chenhao96.service.ATRoleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/role")
public class ATRoleController extends AbstractController {

    @Resource
    private ATRoleService atRoleService;

    @GetMapping("/info/{roleId}")
    @ApiOperation(value = "系统角色查询接口")
    @PreAuthorize("hasAuthority('sys:role:info')")
    @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "Integer")
    public BaseResult<ATRoles> roleInfo(@PathVariable Integer roleId) {
        BaseResult<ATRoles> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "未找到对应信息!");
        if (roleId > 0) {
            ATRoles role = atRoleService.queryRoleById(roleId);
            if (role != null) {
                result.setData(role);
                result.setMsg("操作成功!");
                result.setCode(HttpStatus.OK.value());
            }
        }
        return result;
    }

    @PostMapping("/save")
    @ApiOperation(value = "系统角色添加接口")
    @PreAuthorize("hasAuthority('sys:role:save')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色名称", required = true),
            @ApiImplicitParam(name = "roleTag", value = "角色标签", required = true),
            @ApiImplicitParam(name = "status", value = "角色状态", required = true, paramType = "Boolean")
    })
    public BaseResult<?> saveRoleInfo(ATRoleBo atRoleBo) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "保存失败!");
        if (atRoleService.addNewRole(atRoleBo)) {
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @PostMapping("/update")
    @ApiOperation(value = "系统角色修改接口")
    @PreAuthorize("hasAuthority('sys:role:update')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "roleName", value = "角色名称"),
            @ApiImplicitParam(name = "roleTag", value = "角色标签"),
            @ApiImplicitParam(name = "status", value = "角色状态", paramType = "Boolean")
    })
    public BaseResult<?> updateRoleInfo(ATRoleBo atRoleBo) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "修改失败!");
        if (atRoleService.saveRoleInfo(atRoleBo)) {
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @PostMapping("/pageQuery")
    @ApiOperation(value = "系统角色分页查询接口")
    @PreAuthorize("hasAuthority('sys:role:pageQuery')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "分页每页条数", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "page", value = "当前页第一行行号", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "ascCol", value = "升序字段集", paramType = "Set<String>"),
            @ApiImplicitParam(name = "descCol", value = "降序字段集", paramType = "Set<String>"),
            @ApiImplicitParam(name = "condition", value = "查询条件", paramType = "Map<String,Object>")
    })
    public PageResult<ATRoles> pageQuery(PageQuery query) {
        PageResult<ATRoles> result = new PageResult<>(HttpStatus.NO_CONTENT.value(), "查询无结果!");
        List<ATRoles> list = atRoleService.pageQuery(query);
        if (!CollectionUtils.isEmpty(list)) {
            result.setData(list);
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @DeleteMapping("/delete/{roleId}")
    @ApiOperation(value = "系统角色删除接口")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "Integer")
    public BaseResult<?> roleDelete(@PathVariable Integer roleId) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "删除失败!");
        if (roleId > 0 && atRoleService.deleteRole(roleId)) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("操作成功!");
        }
        return result;
    }

    @ApiOperation(value = "系统角色状态修改接口")
    @PostMapping("/changeStatus")
    @PreAuthorize("hasAuthority('sys:role:changeStatus')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "status", value = "角色状态", required = true, paramType = "Boolean")
    })
    public BaseResult<?> roleStatusChange(ATRoleBo atRoleBo) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "状态修改失败!");
        if (atRoleBo.getId() > 0 && atRoleService.roleStatusChange(atRoleBo.getId(), atRoleBo.getStatus())) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("操作成功!");
        }
        return result;
    }
}
