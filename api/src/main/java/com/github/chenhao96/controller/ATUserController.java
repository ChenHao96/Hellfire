package com.github.chenhao96.controller;

import com.github.chenhao96.annotation.SystemAdminOption;
import com.github.chenhao96.entity.bo.ATUserBo;
import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.enums.UserStatusEnum;
import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.entity.vo.BaseResult;
import com.github.chenhao96.entity.vo.PageResult;
import com.github.chenhao96.service.ATUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class ATUserController extends AbstractController {

    @Resource
    private ATUserService atUserService;

    @GetMapping("/info/{id}")
    @ApiOperation(value = "系统账号查询接口")
    @PreAuthorize("hasAuthority('sys:user:info')")
    @ApiImplicitParam(name = "id", value = "账号id", required = true, paramType = "Integer")
    public BaseResult<ATUsers> userAccountInfo(@PathVariable Integer id) {
        BaseResult<ATUsers> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "未找到对应信息!");
        ATUsers user = atUserService.queryUserAccountById(id);
        if (user != null) {
            result.setData(user);
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @SystemAdminOption
    @PostMapping("/save")
    @ApiOperation(value = "系统账号添加接口")
    @PreAuthorize("hasAuthority('sys:user:save')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名(唯一)", required = true, paramType = "String"),
            @ApiImplicitParam(name = "nickName", value = "昵称", required = true, paramType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱地址", paramType = "String"),
            @ApiImplicitParam(name = "phoneNumber", value = "手机号", required = true, paramType = "String"),
            @ApiImplicitParam(name = "password", value = "登录密码", required = true, paramType = "String"),
            @ApiImplicitParam(name = "optionPassword", value = "操作密码(默认登录密码)", paramType = "String"),
            @ApiImplicitParam(name = "status", value = "账号状态(默认可用)", paramType = "Integer"),
            @ApiImplicitParam(name = "expiredTime", value = "账号过期时间", paramType = "Date")
    })
    public BaseResult<?> saveUserAccount(ATUserBo atUserBo) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "保存失败!");
        if (atUserService.registerNewUser(atUserBo)) {
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @PostMapping("/update")
    @ApiOperation(value = "系统账号修改接口")
    @PreAuthorize("hasAuthority('sys:user:update')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "账号id", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "username", value = "用户名(唯一)", paramType = "String"),
            @ApiImplicitParam(name = "nickName", value = "昵称", paramType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱地址", paramType = "String"),
            @ApiImplicitParam(name = "phoneNumber", value = "手机号", paramType = "String"),
            @ApiImplicitParam(name = "password", value = "登录密码", paramType = "String"),
            @ApiImplicitParam(name = "optionPassword", value = "操作密码", paramType = "String"),
            @ApiImplicitParam(name = "status", value = "账号状态", paramType = "Integer"),
            @ApiImplicitParam(name = "expiredTime", value = "账号过期时间", paramType = "Date")
    })
    public BaseResult<?> updateUserAccount(ATUserBo atUserBo) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "修改失败!");
        if (atUserService.saveUserAccount(atUserBo)) {
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @PostMapping("/pageQuery")
    @ApiOperation(value = "系统账号分页查询接口")
    @PreAuthorize("hasAuthority('sys:user:pageQuery')")
    public BaseResult<PageResult<ATUsers>> userPageQuery(PageQuery query, @RequestParam Map<String, Object> condition) {
        BaseResult<PageResult<ATUsers>> result = new BaseResult<>(HttpStatus.NO_CONTENT.value(), "查询无结果!");
        PageResult<ATUsers> page = atUserService.pageQuery(query);
        if (page != null) {
            result.setData(page);
            result.setMsg("操作成功!");
            result.setCode(HttpStatus.OK.value());
        }
        return result;
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "系统账号删除接口")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    @ApiImplicitParam(name = "id", value = "账号id", required = true, paramType = "Integer")
    public BaseResult<?> userDelete(@PathVariable Integer id) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "删除失败!");
        if (atUserService.deleteUserAccount(id)) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("操作成功!");
        }
        return result;
    }

    @ApiOperation(value = "系统账号状态修改接口")
    @PostMapping("/changeStatus")
    @PreAuthorize("hasAuthority('sys:user:changeStatus')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "账号id", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "status", value = "账号状态", required = true, paramType = "[DISABLE,ENABLE,LOCKED]")
    })
    public BaseResult<?> userStatusChange(Integer id, UserStatusEnum status) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "状态修改失败!");
        if (atUserService.userAccountStatusChange(id, status)) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("操作成功!");
        }
        return result;
    }
}
