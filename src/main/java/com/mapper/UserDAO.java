package com.mapper;

import com.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper

public interface UserDAO {
    //保存用户的方法,注册
    void save(User user);

    //登录的方法
    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User getUserByUsernameAndPassword(String username, String password);

    @Select("SELECT * FROM user WHERE email = #{email} AND password = #{password}")
    User getUserByEmailAndPassword(String email, String password);
}
