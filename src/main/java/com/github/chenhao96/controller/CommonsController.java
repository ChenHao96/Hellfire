package com.github.chenhao96.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommonsController {

    @GetMapping("/")
    public ModelAndView indexPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView loginPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        Boolean isError = (Boolean) request.getAttribute("isError");
        modelAndView.addObject("isError", isError == null ? false : isError);
        modelAndView.addObject("message", request.getAttribute("message"));
        return modelAndView;
    }

    @RequestMapping("/loginExpired")
    public ModelAndView loginExpired(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        modelAndView.addObject("isError", true);
        modelAndView.addObject("message", "您的账号在其他设备上登录，如有异常及时联系管理员！");
        return modelAndView;
    }

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
