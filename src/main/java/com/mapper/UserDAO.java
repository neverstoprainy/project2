package com.mapper;

import com.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper

public interface UserDAO {
    //保存用户的方法,注册
    void save(User user);

    //登录的方法
    User findByUsernameAndPassword(User user);
}
