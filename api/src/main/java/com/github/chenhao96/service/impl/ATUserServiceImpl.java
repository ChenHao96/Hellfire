package com.github.chenhao96.service.impl;

import com.github.chenhao96.adaptor.ATUserAdaptor;
import com.github.chenhao96.entity.bo.ATUserBo;
import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.enums.UserStatusEnum;
import com.github.chenhao96.entity.po.ATUsers;
import com.github.chenhao96.entity.vo.PageResult;
import com.github.chenhao96.service.ATUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class ATUserServiceImpl implements ATUserService {

    @Resource
    private ATUserAdaptor atUserAdaptor;

    @Override
    @Transactional
    public boolean registerNewUser(ATUserBo user) {
        if (user == null || StringUtils.isEmpty(user.getUsername())) return false;
        if (atUserAdaptor.queryUsernameNotExist(user.getUsername())) {
            ATUsers record = new ATUsers();
            BeanUtils.copyProperties(user, record);
            if (record.getStatus() == null) {
                record.setStatus(UserStatusEnum.ENABLE);
            }
            if (StringUtils.isEmpty(record.getOptionPassword()))
                record.setOptionPassword(record.getPassword());
            record.setId(null);
            if (!atUserAdaptor.saveNewRecord(record)) {
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
        return atUserAdaptor.queryRecordById(userId);
    }

    @Override
    @Transactional
    public boolean saveUserAccount(ATUserBo user) {
        if (user == null || user.getId() == null) return false;
        checkUserName(user);
        ATUsers record = new ATUsers();
        BeanUtils.copyProperties(user, record);
        if (!atUserAdaptor.updateRecordInfo(record)) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    private void checkUserName(ATUserBo user) {
        String username = user.getUsername();
        user.setUsername(null);
        if (StringUtils.hasText(username)) {
            if (atUserAdaptor.queryUsernameNotExist(user.getUsername())) {
                user.setUsername(username);
            }
        }
    }

    @Override
    public PageResult<ATUsers> pageQuery(PageQuery query) {
        if (query == null) return null;
        return atUserAdaptor.queryPage(query);
    }

    @Override
    @Transactional
    public boolean deleteUserAccount(Integer userId) {
        if (userId == null || userId < 1) return false;
        if (!atUserAdaptor.deleteRecord(userId)) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean userAccountStatusChange(Integer userId, UserStatusEnum status) {
        return saveUserAccount(new ATUserBo(userId, status));
    }

    @Override
    public ATUsers queryByUsername(String username) {
        if (StringUtils.isEmpty(username)) return null;
        return atUserAdaptor.queryUserByUsername(username);
    }
}
