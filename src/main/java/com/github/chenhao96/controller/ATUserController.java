package com.github.chenhao96.controller;

import com.github.chenhao96.entity.bo.ATUserBo;
import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.enums.UserStatusEnum;
import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.entity.vo.BaseResult;
import com.github.chenhao96.entity.vo.PageResult;
import com.github.chenhao96.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class ATUserController extends AbstractController {

    @Resource
    private UserService userService;

    @GetMapping("/addPage")
    @PreAuthorize("hasAuthority('sys:user:addPage')")
    public ModelAndView addUserAccount() {
        ModelAndView result = new ModelAndView("/user/userInfoPage");
        result.addObject("doAdd", true);
        return result;
    }

    @GetMapping("/infoPage/{userId}")
    @PreAuthorize("hasAuthority('sys:user:infoPage')")
    public ModelAndView userAccountInfo(@PathVariable Integer userId) {
        ModelAndView result = new ModelAndView("/user/userInfoPage");
        result.addObject("query", false);
        result.addObject("errMsg", "未找到对应的账号信息!");
        if (userId != null && userId > 0) {
            ATUsers user = userService.queryUserAccountById(userId);
            if (user != null) {
                result.addObject("userInfo", user);
                result.addObject("query", true);
            }
        }
        return result;
    }

    @GetMapping("/updatePage/{userId}")
    @PreAuthorize("hasAuthority('sys:user:updatePage')")
    public ModelAndView updateUserAccount(@PathVariable Integer userId) {
        ModelAndView result = userAccountInfo(userId);
        result.addObject("doAdd", false);
        return result;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:user:save')")
    public ModelAndView saveUserAccount(ATUserBo atUserBo) {
        ModelAndView result = new ModelAndView("/user/userInfoPage");
        result.addObject("userInfo", atUserBo);
        result.addObject("save", false);
        result.addObject("errMsg", "未找到对应的账号信息!");
        if (userService.saveUserAccount(atUserBo)) {
            return userListPage(null);
        }
        return result;
    }

    @GetMapping("/listPage/{pageCount}")
    @PreAuthorize("hasAuthority('sys:user:listPage')")
    public ModelAndView userListPage(@PathVariable Integer pageCount) {
        if (pageCount == null) pageCount = 20;
        ModelAndView result = new ModelAndView("/user/userList");
        List<ATUsers> userList = userService.queryTopCountList(pageCount);
        result.addObject("pageList", userList);
        return result;
    }

    @RequestMapping("/pageQuery")//JSON api
    @PreAuthorize("hasAuthority('sys:user:pageQuery')")
    public PageResult<ATUsers> pageQuery(PageQuery query) {
        return userService.pageQuery(query);
    }

    @GetMapping("/delete/{userId}")//JSON api
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public BaseResult<?> userDelete(@PathVariable Integer userId) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "删除失败！");
        if (userId > 0 && userService.deleteUserAccount(userId)) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("账号删除成功！");
        }
        return result;
    }

    @GetMapping("/changeStatus/{userId}")//JSON api
    @PreAuthorize("hasAuthority('sys:user:changeStatus')")
    public BaseResult<?> userStatusChange(@PathVariable Integer userId, UserStatusEnum status) {
        BaseResult<?> result = new BaseResult<>(HttpStatus.ACCEPTED.value(), "修改失败！");
        if (userId > 0 && userService.updateUserAccountStatus(userId, status)) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("状态修改成功！");
        }
        return result;
    }
}
