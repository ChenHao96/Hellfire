package com.github.chenhao96.controller;

import com.github.chenhao96.entity.bo.ATMenuBo;
import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.po.ATMenus;
import com.github.chenhao96.entity.vo.BaseResult;
import com.github.chenhao96.entity.vo.PageResult;
import com.github.chenhao96.service.ATMenuService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class ATMenuController extends AbstractController {

    @Resource
    private ATMenuService atMenuService;

    @GetMapping("/info/{id}")
    @ApiOperation(value = "系统菜单查询接口")
    @PreAuthorize("hasAuthority('sys:menu:info')")
    @ApiImplicitParam(name = "id", value = "菜单id", required = true, paramType = "Integer")
    public BaseResult<ATMenus> menuInfo(@PathVariable Integer id) {
        BaseResult<ATMenus> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "未找到对应信息!");
        ATMenus menu = atMenuService.queryMenuById(id);
        if (menu != null) {
            result.setData(menu);
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @PostMapping("/save")
    @ApiOperation(value = "系统菜单添加接口")
    @PreAuthorize("hasAuthority('sys:menu:save')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menuName", value = "菜单名称", required = true, paramType = "String"),
            @ApiImplicitParam(name = "menuIndex", value = "菜单索引", required = true, paramType = "String"),
            @ApiImplicitParam(name = "menuNo", value = "菜单序号", required = true, paramType = "String"),
            @ApiImplicitParam(name = "status", value = "菜单状态", required = true, paramType = "Boolean")
    })
    public BaseResult<?> saveMenuInfo(ATMenuBo atMenuBo) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "保存失败!");
        if (atMenuService.addNewMenu(atMenuBo)) {
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @PostMapping("/update")
    @ApiOperation(value = "系统菜单修改接口")
    @PreAuthorize("hasAuthority('sys:menu:update')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单id", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "menuName", value = "菜单名称", paramType = "String"),
            @ApiImplicitParam(name = "menuIndex", value = "菜单索引", paramType = "String"),
            @ApiImplicitParam(name = "menuNo", value = "菜单序号", paramType = "String"),
            @ApiImplicitParam(name = "status", value = "菜单状态", paramType = "Boolean")
    })
    public BaseResult<?> updateMenuInfo(ATMenuBo atMenuBo) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "修改失败!");
        if (atMenuService.saveMenuInfo(atMenuBo)) {
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @PostMapping("/pageQuery")
    @ApiOperation(value = "系统菜单分页查询接口")
    @PreAuthorize("hasAuthority('sys:menu:pageQuery')")
    public BaseResult<PageResult<ATMenus>> menuPageQuery(PageQuery query, @RequestParam Map<String, Object> condition) {
        BaseResult<PageResult<ATMenus>> result = new BaseResult<>(HttpStatus.NO_CONTENT.value(), "查询无结果!");
        PageResult<ATMenus> page = atMenuService.pageQuery(query);
        if (page != null) {
            result.setData(page);
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "系统菜单删除接口")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @ApiImplicitParam(name = "id", value = "菜单id", required = true, paramType = "Integer")
    public BaseResult<?> menuDelete(@PathVariable Integer id) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "删除失败!");
        if (atMenuService.deleteMenu(id)) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("操作成功!");
        }
        return result;
    }

    @ApiOperation(value = "系统菜单状态修改接口")
    @PostMapping("/changeStatus")
    @PreAuthorize("hasAuthority('sys:menu:changeStatus')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单id", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "status", value = "菜单状态", required = true, paramType = "Boolean")
    })
    public BaseResult<?> menuStatusChange(Integer id, Boolean status) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "状态修改失败!");
        if (atMenuService.menuStatusChange(id, status)) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("操作成功!");
        }
        return result;
    }
}
