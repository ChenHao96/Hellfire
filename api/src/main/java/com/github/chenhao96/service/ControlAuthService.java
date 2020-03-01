package com.github.chenhao96.service;

import com.github.chenhao96.entity.po.ATControls;
import com.github.chenhao96.entity.vo.AuthUrlControls;

import java.util.List;
import java.util.concurrent.Future;

public interface ControlAuthService {

    List<ATControls> findControlsByUserId(Integer userId);

    Future<List<AuthUrlControls>> queryAuthoritiesByUser(Integer userId);
}
