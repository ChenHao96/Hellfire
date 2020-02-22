package com.github.chenhao96.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommonsController extends AbstractController {

    @RequestMapping("/")
    public ModelAndView indexPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView loginPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        Boolean isError = (Boolean) request.getAttribute("isError");
        modelAndView.addObject("isError", isError == null ? false : isError);
        modelAndView.addObject("message", request.getAttribute("message"));
        return modelAndView;
    }

    @ResponseBody
    @GetMapping("/test")
    @PreAuthorize("hasAuthority('commons:test')")
    public String testPage() {
        return "test";
    }
}
