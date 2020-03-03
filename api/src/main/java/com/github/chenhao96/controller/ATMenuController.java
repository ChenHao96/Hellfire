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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class ATMenuController extends AbstractController {

    @Resource
    private ATMenuService atMenuService;

    @GetMapping("/info/{menuId}")
    @ApiOperation(value = "系统菜单查询接口")
    @PreAuthorize("hasAuthority('sys:menu:info')")
    @ApiImplicitParam(name = "menuId", value = "菜单id", required = true)
    public BaseResult<ATMenus> menuInfo(@PathVariable Integer menuId) {
        BaseResult<ATMenus> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "未找到对应信息!");
        if (menuId > 0) {
            ATMenus menu = atMenuService.queryMenuById(menuId);
            if (menu != null) {
                result.setData(menu);
                result.setMsg("操作成功!");
                result.setCode(HttpStatus.OK.value());
            }
        }
        return result;
    }

    @PostMapping("/save")
    @ApiOperation(value = "系统菜单添加接口")
    @PreAuthorize("hasAuthority('sys:menu:save')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menuName", value = "菜单名称", required = true),
            @ApiImplicitParam(name = "menuIndex", value = "菜单索引", required = true),
            @ApiImplicitParam(name = "menuNo", value = "菜单序号", required = true),
            @ApiImplicitParam(name = "status", value = "菜单状态", required = true)
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
            @ApiImplicitParam(name = "id", value = "菜单id", required = true),
            @ApiImplicitParam(name = "menuName", value = "菜单名称"),
            @ApiImplicitParam(name = "menuIndex", value = "菜单索引"),
            @ApiImplicitParam(name = "menuNo", value = "菜单序号"),
            @ApiImplicitParam(name = "status", value = "菜单状态")
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "分页每页条数", required = true),
            @ApiImplicitParam(name = "page", value = "当前页第一行行号", required = true),
            @ApiImplicitParam(name = "ascCol", value = "升序字段集"),
            @ApiImplicitParam(name = "descCol", value = "降序字段集"),
            @ApiImplicitParam(name = "condition", value = "查询条件")
    })
    public PageResult<ATMenus> pageQuery(PageQuery query) {
        PageResult<ATMenus> result = new PageResult<>(HttpStatus.NO_CONTENT.value(), "查询无结果!");
        List<ATMenus> list = atMenuService.pageQuery(query);
        if (!CollectionUtils.isEmpty(list)) {
            result.setData(list);
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @DeleteMapping("/delete/{menuId}")
    @ApiOperation(value = "系统菜单删除接口")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @ApiImplicitParam(name = "menuId", value = "菜单id", required = true)
    public BaseResult<?> menuDelete(@PathVariable Integer menuId) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "删除失败!");
        if (menuId > 0 && atMenuService.deleteMenu(menuId)) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("操作成功!");
        }
        return result;
    }

    @ApiOperation(value = "系统菜单状态修改接口")
    @PostMapping("/changeStatus")
    @PreAuthorize("hasAuthority('sys:menu:changeStatus')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单id", required = true),
            @ApiImplicitParam(name = "status", value = "菜单状态", required = true)
    })
    public BaseResult<?> menuStatusChange(ATMenuBo atMenuBo) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "状态修改失败!");
        if (atMenuBo.getId() > 0 && atMenuService.menuStatusChange(atMenuBo.getId(), atMenuBo.getStatus())) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("操作成功!");
        }
        return result;
    }
}
