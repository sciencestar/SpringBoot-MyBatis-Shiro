package com.dao;

import com.domin.User;

import java.util.List;

public interface UserDao<T> {

    List<User> selectId(String username);

    User findUserByName(String userName);

}
