package com.github.chenhao96.controller;

import com.github.chenhao96.annotation.SystemAdminOption;
import com.github.chenhao96.entity.bo.ATControlBo;
import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.po.ATControls;
import com.github.chenhao96.entity.vo.BaseResult;
import com.github.chenhao96.entity.vo.PageResult;
import com.github.chenhao96.service.ATControlService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/control")
public class ATControlController extends AbstractController {

    @Resource
    private ATControlService atControlService;

    @GetMapping("/info/{id}")
    @ApiOperation(value = "系统权限查询接口")
    @PreAuthorize("hasAuthority('sys:control:info')")
    @ApiImplicitParam(name = "id", value = "权限id", required = true, paramType = "Integer")
    public BaseResult<ATControls> controlInfo(@PathVariable Integer id) {
        BaseResult<ATControls> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "未找到对应信息!");
        ATControls controls = atControlService.queryControlById(id);
        if (controls != null) {
            result.setData(controls);
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @SystemAdminOption
    @PostMapping("/save")
    @ApiOperation(value = "系统权限添加接口")
    @PreAuthorize("hasAuthority('sys:control:save')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menuId", value = "菜单id", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "optionName", value = "权限名称", required = true, paramType = "String"),
            @ApiImplicitParam(name = "buttonIndex", value = "权限索引", required = true, paramType = "String"),
            @ApiImplicitParam(name = "optionTag", value = "权限标签", required = true, paramType = "String"),
            @ApiImplicitParam(name = "needPassword", value = "是否需要密码", required = true, paramType = "Boolean"),
            @ApiImplicitParam(name = "warringPrompt", value = "是否操作警告", required = true, paramType = "Boolean"),
            @ApiImplicitParam(name = "status", value = "权限状态", required = true, paramType = "Boolean")
    })
    public BaseResult<?> saveControlInfo(ATControlBo bo) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "保存失败!");
        if (atControlService.addNewControl(bo)) {
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @PostMapping("/update")
    @ApiOperation(value = "系统权限修改接口")
    @PreAuthorize("hasAuthority('sys:control:update')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "权限id", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "menuId", value = "菜单id", paramType = "Integer"),
            @ApiImplicitParam(name = "optionName", value = "权限名称", paramType = "String"),
            @ApiImplicitParam(name = "buttonIndex", value = "权限索引", paramType = "String"),
            @ApiImplicitParam(name = "optionTag", value = "权限标签", paramType = "String"),
            @ApiImplicitParam(name = "needPassword", value = "是否需要密码", paramType = "Boolean"),
            @ApiImplicitParam(name = "warringPrompt", value = "是否操作警告", paramType = "Boolean"),
            @ApiImplicitParam(name = "status", value = "权限状态", paramType = "Boolean")
    })
    public BaseResult<?> updateControlInfo(ATControlBo bo) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "修改失败!");
        if (atControlService.saveControlInfo(bo)) {
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @PostMapping("/pageQuery")
    @ApiOperation(value = "系统权限分页查询接口")
    @PreAuthorize("hasAuthority('sys:control:pageQuery')")
    public BaseResult<PageResult<ATControls>> controlPageQuery(PageQuery query, @RequestParam Map<String, Object> condition) {
        BaseResult<PageResult<ATControls>> result = new BaseResult<>(HttpStatus.NO_CONTENT.value(), "查询无结果!");
        PageResult<ATControls> page = atControlService.pageQuery(query);
        if (page != null) {
            result.setData(page);
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "系统权限删除接口")
    @PreAuthorize("hasAuthority('sys:control:delete')")
    @ApiImplicitParam(name = "id", value = "权限id", required = true, paramType = "Integer")
    public BaseResult<?> controlDelete(@PathVariable Integer id) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "删除失败!");
        if (atControlService.deleteControl(id)) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("操作成功!");
        }
        return result;
    }

    @ApiOperation(value = "系统权限状态修改接口")
    @PostMapping("/changeStatus")
    @PreAuthorize("hasAuthority('sys:control:changeStatus')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "权限id", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "status", value = "权限状态", required = true, paramType = "Boolean")
    })
    public BaseResult<?> controlStatusChange(Integer id, Boolean status) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "状态修改失败!");
        if (atControlService.controlStatusChange(id, status)) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("操作成功!");
        }
        return result;
    }
}
