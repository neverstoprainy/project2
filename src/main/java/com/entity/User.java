package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * @author zym
 * @version 1.0
 * @description: 封装用户
 * @date 2024/5/30 23:50
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    private String username;	// 用户名
    private String password;	// 密码
    private String email;       //邮箱
}
