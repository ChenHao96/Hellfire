package com.github.chenhao96.service;

public interface VerificationSendCodeService {

    void deviceEmail(String toAddress, String code);

    void deviceMobilePhone(String toAddress, String code);
}
