package com.github.chenhao96.service;

import com.github.chenhao96.entity.po.ATControls;
import com.github.chenhao96.entity.vo.AuthUrlControls;

import java.util.List;

public interface ControlAuthService {

    List<ATControls> findControlsByUserId(Integer userId);

    List<AuthUrlControls> queryAuthoritiesByUser(Integer userId);
}
