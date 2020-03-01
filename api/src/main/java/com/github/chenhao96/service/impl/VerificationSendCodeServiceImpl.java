package com.github.chenhao96.service.impl;

import com.github.chenhao96.service.VerificationSendCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VerificationSendCodeServiceImpl implements VerificationSendCodeService {

    @Async
    @Override
    public void deviceEmail(String toAddress, String code) {
        //TODO:
        log.info("deviceEmail code:{}", code);
    }

    @Async
    @Override
    public void deviceMobilePhone(String toAddress, String code) {
        //TODO:
        log.info("deviceMobilePhone code:{}", code);
    }
}
