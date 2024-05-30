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
    private User user;
    private String emailCode;
    private String captcha;
    private String captchaId;

    // Getters and Setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getemailCode() {
        return emailCode;
    }

    public void setemailCode(String emailCode) {
        this.emailCode = emailCode;
    }

    public String getcaptcha() {
        return captcha;
    }

    public void setcaptcha(String captcha) {
        this.captcha = captcha;
    }
}
