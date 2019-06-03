package com.example.server.soa.service.user.impl;

import com.example.server.soa.common.constants.LoginConstants;
import com.example.server.soa.common.util.MD5Util;
import com.example.server.soa.dao.mysql.domain.User;
import com.example.server.soa.dao.mysql.mapper.UserMapper;
import com.example.server.soa.service.base.BaseService;
import com.example.server.soa.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean createUser(User user) {
        String password = user.getPassword();
        if (StringUtils.isBlank(password)) {
            return false;
        }
        String md5Password = MD5Util.md5Hex(password);
        if (StringUtils.isBlank(md5Password) || md5Password.length() != LoginConstants.MD5_PASSWORD_LENGTH) {
            return false;
        }
        user.setPassword(md5Password);
        return userMapper.insert(user) == 1;
    }

    @Override
    public User getUserById(Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null || user.getUserId() == null) {
            return null;
        }
        user.setPassword(null);
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(User user) {
        if (user.getUserId() == null) {
            return false;
        }
        if (user.getPassword() != null) {
            String password = MD5Util.md5Hex(user.getPassword());
            if (StringUtils.isBlank(password) || password.length() != LoginConstants.MD5_PASSWORD_LENGTH) {
                return false;
            }
            user.setPassword(password);
        }
        return userMapper.updateById(user) == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUserById(Long userId) {
        return userMapper.deleteById(userId) == 1;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public User login(String loginName, String password, String loginType) {
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(password)) {
            return null;
        }
        log.info("login loginName:{}, password:{}, loginType:{} ", loginName, password, loginType);
        User user = null;
        if (LoginConstants.EMAIL.equals(loginType)) {
            user = userMapper.selectByEmailWithPassword(loginName);
        } else if (LoginConstants.MOBILE.equals(loginType)) {
            user = userMapper.selectByMobileWithPassword(loginName);
        } else if (LoginConstants.USERNAME.equals(loginType)) {
            user = userMapper.selectByUsernameWithPassword(loginName);
        }
        if (user == null) {
            return null;
        }
        if (!MD5Util.md5Hex(password).equals(user.getPassword())) {
            return null;
        }
        user.setPassword(null);
        return user;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public boolean check(String loginName, String loginType) {
        if (StringUtils.isBlank(loginName)) {
            return false;
        }
        User user = null;
        if (LoginConstants.EMAIL.equals(loginType)) {
            user = userMapper.selectByEmailWithPassword(loginName);
        } else if (LoginConstants.MOBILE.equals(loginType)) {
            user = userMapper.selectByMobileWithPassword(loginName);
        } else if (LoginConstants.USERNAME.equals(loginType)) {
            user = userMapper.selectByUsernameWithPassword(loginName);
        }
        return (user != null) && (user.getUserId() != null);
    }
}
