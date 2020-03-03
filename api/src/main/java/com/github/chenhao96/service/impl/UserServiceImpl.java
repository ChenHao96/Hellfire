package com.github.chenhao96.service.impl;

import com.github.chenhao96.adaptor.ATUserAdaptor;
import com.github.chenhao96.entity.bo.ATUserBo;
import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.enums.UserStatusEnum;
import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

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
            record.setStatus(UserStatusEnum.ENABLE);
            if (user.getStatus() != null) {
                record.setStatus(UserStatusEnum.fromCode(user.getStatus()));
            }
            BeanUtils.copyProperties(user, record);
            if (StringUtils.isEmpty(record.getOptionPassword()))
                record.setOptionPassword(record.getPassword());
            record.setId(null);
            if (!atUserAdaptor.saveNewAtUser(record)) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public ATUsers queryUserAccountById(Integer userId) {
        if (userId == null || userId < 1) return null;
        ATUsers result = atUserAdaptor.queryUserAccountById(userId);
        //TODO:
        return result;
    }

    @Override
    @Transactional
    public boolean saveUserAccount(ATUserBo user) {
        if (user == null || user.getId() == null) return false;
        checkUserName(user);
        ATUsers record = new ATUsers();
        BeanUtils.copyProperties(user, record);
        if (user.getStatus() != null) {
            record.setStatus(UserStatusEnum.fromCode(user.getStatus()));
        }
        if (!atUserAdaptor.updateAtUserInfo(record)) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    private void checkUserName(ATUserBo user) {
        String username = user.getUsername();
        user.setUsername(null);
        if (StringUtils.hasText(username)) {
            if (!atUserAdaptor.queryUsernameExist(user.getUsername())) {
                user.setUsername(username);
            }
        }
    }

    @Override
    public List<ATUsers> pageQuery(PageQuery query) {
        //TODO:
        return null;
    }

    @Override
    @Transactional
    public boolean deleteUserAccount(Integer userId) {
        if (userId == null || userId < 1) return false;
        if (!atUserAdaptor.deleteUserAccount(userId)) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean userAccountStatusChange(Integer userId, Integer status) {
        ATUserBo user = new ATUserBo();
        user.setId(userId);
        user.setStatus(status);
        return saveUserAccount(user);
    }

    @Override
    public ATUsers queryByUsername(String username) {
        if (StringUtils.isEmpty(username)) return null;
        return atUserAdaptor.queryUserByUsername(username);
    }
}
