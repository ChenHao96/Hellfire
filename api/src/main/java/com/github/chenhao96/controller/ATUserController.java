package com.github.chenhao96.controller;

import com.github.chenhao96.entity.bo.ATUserBo;
import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.entity.vo.BaseResult;
import com.github.chenhao96.entity.vo.PageResult;
import com.github.chenhao96.service.UserService;
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
@RequestMapping("/user")
public class ATUserController extends AbstractController {

    @Resource
    private UserService userService;

    @GetMapping("/info/{userId}")
    @ApiOperation(value = "系统账号查询接口")
    @PreAuthorize("hasAuthority('sys:user:info')")
    @ApiImplicitParam(name = "userId", value = "系统账号id", required = true)
    public BaseResult<ATUsers> userAccountInfo(@PathVariable Integer userId) {
        BaseResult<ATUsers> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "未找到对应信息!");
        if (userId > 0) {
            ATUsers user = userService.queryUserAccountById(userId);
            if (user != null) {
                result.setData(user);
                result.setMsg("操作成功!");
                result.setCode(HttpStatus.OK.value());
            }
        }
        return result;
    }

    @PostMapping("/save")
    @ApiOperation(value = "系统账号添加接口")
    @PreAuthorize("hasAuthority('sys:user:save')")
    public BaseResult<?> saveUserAccount(ATUserBo atUserBo) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "保存失败!");
        if (userService.registerNewUser(atUserBo)) {
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @PostMapping("/update")
    @ApiOperation(value = "系统账号修改接口")
    @PreAuthorize("hasAuthority('sys:user:update')")
    public BaseResult<?> updateUserAccount(ATUserBo atUserBo) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "修改失败!");
        if (userService.saveUserAccount(atUserBo)) {
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @PostMapping("/pageQuery")
    @ApiOperation(value = "系统账号分页查询接口")
    @PreAuthorize("hasAuthority('sys:user:pageQuery')")
    public PageResult<ATUsers> pageQuery(PageQuery query) {
        PageResult<ATUsers> result = new PageResult<>(HttpStatus.NO_CONTENT.value(), "查询无结果!");
        List<ATUsers> list = userService.pageQuery(query);
        if(!CollectionUtils.isEmpty(list)){
            result.setData(list);
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @PostMapping("/delete/{userId}")
    @ApiOperation(value = "系统账号删除接口")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    @ApiImplicitParam(name = "userId", value = "系统账号id", required = true)
    public BaseResult<?> userDelete(@PathVariable Integer userId) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "删除失败!");
        if (userId > 0 && userService.deleteUserAccount(userId)) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("操作成功!");
        }
        return result;
    }

    @ApiOperation(value = "系统账号状态修改接口")
    @PostMapping("/changeStatus/{userId}/{status}")
    @PreAuthorize("hasAuthority('sys:user:changeStatus')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "系统账号id", required = true),
            @ApiImplicitParam(name = "status", value = "账号状态", required = true)
    })
    public BaseResult<?> userStatusChange(@PathVariable Integer userId, @PathVariable Integer status) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "状态修改失败!");
        if (userId > 0 && userService.userAccountStatusChange(userId, status)) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("操作成功!");
        }
        return result;
    }
}
