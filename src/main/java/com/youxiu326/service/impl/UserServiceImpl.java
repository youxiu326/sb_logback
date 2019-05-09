package com.youxiu326.service.impl;

import com.youxiu326.controller.UserController;
import com.youxiu326.mapper.UserMapper;
import com.youxiu326.model.User;
import com.youxiu326.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUser(String userId) {
        return userMapper.selectUser(userId);
    }

    @Override
    public void byZero() {
        logger.debug("debug log");
        logger.info("info log");
        logger.error("error log");
        int error = 4/0;
    }
}
