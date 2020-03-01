package com.controller;

import com.domin.User;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/getUserByName")
    public User getUserByName(String userName){
        User user = userService.getUserByName(userName);
        return user;
    }
}
