package com.entity;

import lombok.Data;

/**
 * @author zym
 * @version 1.0
 * @description: 封装用户注册信息
 * @date 2024/5/30 0:09
 */

@Data
public class UserRegistrationRequest {
    private String username;	// 用户名
    private String password;	// 密码
    private String email;       //邮箱
    private String emailCode;
    private String captcha;
    private String captchaId;
}
