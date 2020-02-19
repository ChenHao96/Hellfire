package com.github.chenhao96.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WelcomeController.class);

    @GetMapping("/hello")
    public String hello(Device device) {
        String deviceStr = "normal";
        if (!device.isNormal()) {
            if (device.isMobile()) {
                deviceStr = "mobile";
            } else if (device.isTablet()) {
                deviceStr = "tablet";
            } else {
                deviceStr = "unknown";
            }
        }
        LOGGER.info("request device {}...", deviceStr);
        return "hello world!";
    }
}
