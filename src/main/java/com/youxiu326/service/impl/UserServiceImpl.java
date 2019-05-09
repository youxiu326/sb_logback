package com.youxiu326.service.impl;

import com.youxiu326.mapper.UserMapper;
import com.youxiu326.model.User;
import com.youxiu326.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUser(String userId) {
        return userMapper.selectUser(userId);
    }
}
