package com.github.chenhao96.service.impl;

import com.github.chenhao96.adaptor.ATUserAdaptor;
import com.github.chenhao96.entity.bo.ATUserBo;
import com.github.chenhao96.entity.enums.UserStatusEnum;
import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private ATUserAdaptor atUserAdaptor;

    @Override
    @Transactional
    public boolean registerNewUser(ATUserBo user) {
        if (user == null || StringUtils.isEmpty(user.getUsername())) return false;
        if (!atUserAdaptor.queryUsernameExist(user.getUsername())) {

            ATUsers record = new ATUsers();
            record.setStatus(UserStatusEnum.fromCode(user.getStatus()));
            BeanUtils.copyProperties(user, record);
            if (StringUtils.isEmpty(record.getOptionPassword()))
                record.setOptionPassword(record.getPassword());

            return atUserAdaptor.saveNewAtUser(record);
        }
        return false;
    }
}
