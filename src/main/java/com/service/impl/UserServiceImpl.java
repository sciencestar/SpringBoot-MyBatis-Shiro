package com.service.impl;

import com.dao.UserDao;
import com.domin.User;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByName(String userName) {
        User user = userDao.findUserByName(userName);
        return user;
    }
}
