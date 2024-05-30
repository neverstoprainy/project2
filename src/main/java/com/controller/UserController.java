package com.controller;
import com.entity.CaptchaData;
import  com.entity.User;
import  com.entity.Result;


import com.entity.UserRegistrationRequest;
import com.service.UserService;
import com.utils.EmailService;
import com.utils.PictureCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private User user=new User();
    @Autowired
    private PictureCodeService pictureCodeService;
    @Autowired(required = false)
    private UserService userService; //注入
    @Autowired
    private EmailService emailService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    //开发用户登录
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User u = userService.login(user);
        if(u != null){
            log.info("找到目标用户");
            return Result.success(u);
        }else{
            return Result.error("Invalid username or password");
        }
    }

    //开发用户注册
    @PostMapping("/register")
    public Result register(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        System.out.println("user = " + userRegistrationRequest.getUsername());
        try {
            // 获取用户输入的验证码和存储的验证码
            String email = userRegistrationRequest.getEmail();
            log.info("邮箱码：{}",email);
            //从redis中取出验证码信息
            String code = redisTemplate.opsForValue().get(email);
            String enteredCode = userRegistrationRequest.getEmailCode();

            log.info(redisTemplate.opsForValue().get("u.lpnjog@qq.com"));
            // 验证图形验证码
            log.info(userRegistrationRequest.getCaptcha());
            if (!pictureCodeService.validateCaptcha(email, userRegistrationRequest.getCaptcha())) {
                return Result.error("图形验证码输入错误");
            }

            user.setUsername(userRegistrationRequest.getUsername());
            user.setEmail(userRegistrationRequest.getEmail());
            user.setPassword(userRegistrationRequest.getPassword());

            // 验证邮箱验证码
            if (redisTemplate.opsForValue().get(email) != null &&redisTemplate.opsForValue().get(email).equals(enteredCode)) {
                userService.save(user);
                return Result.success("注册成功");
            } else {
                return Result.error("邮箱验证码验证码错误");
            }
        } catch (Exception e) {
            System.out.println(e);
            return Result.error("注册失败");
        }

    }
    @Autowired(required = false)
    private CaptchaData captchaData;

    // 生成图形验证码的端点
    @GetMapping("/generateCaptcha")
    public Result generateCaptcha(@RequestParam String email) {
       captchaData.setId(redisTemplate.opsForValue().get(email + "_captcha_id"));
       captchaData.setBase64(pictureCodeService.generateCaptcha(email));
        return Result.success(captchaData);
    }

    //发送邮箱验证码
    @PostMapping("/sendCode")
    public Result sendCode(@RequestParam String email) {
        try {
            emailService.mail(email);
            return Result.success("验证码已发送");
        } catch (MessagingException e) {
            return Result.error("验证码发送失败");
        }
    }
}


