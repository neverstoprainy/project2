package com.service;
import com.entity.User;


public interface UserService {
    //注册
    void save(User user);
    //登录
    User login(User user);
}