package com.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.Duration;

@Service
//public class EmailService {
//
//    @Resource
//    private JavaMailSenderImpl mailSender;
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//    public String generateBase64Code(String email) throws MessagingException{
//
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        //生成随机验证码
//        String code = CodeGeneratorUtil.generateCode(6);
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//        //设置一个html邮件信息
//        helper.setText("<p style='color: blue'>你的邮箱验证码为：" + code + "(有效期为一分钟)</p>", true);
//        //设置邮件主题名
//        helper.setSubject("FlowerPotNet验证码----验证码");
//        //发给谁-》邮箱地址
//        helper.setTo(email);
//        //谁发的-》发送人邮箱
//        helper.setFrom("1849326010@qq.com");
//        //将邮箱验证码以邮件地址为key存入redis,1分钟过期
//        redisTemplate.opsForValue().set(email, code, Duration.ofMinutes(1));//opsForValue() 是 RedisTemplate 提供的一个方法，用于获取用于操作 Redis 字符串类型数据的 ValueOperations 对象
//        mailSender.send(mimeMessage);
//        return CodeGeneratorUtil.generateBase64Code(6);
//    }
//
//    public String getStoredCode(String email) {
//        return redisTemplate.opsForValue().get(email);
//    }
//}


public class EmailService {

    @Resource
    private JavaMailSenderImpl mailSender;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public boolean mail(String email) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //生成随机验证码
        String code = CodeGeneratorUtil.generateCode(6);
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //设置一个html邮件信息
        helper.setText("<p style='color: blue'>你的邮箱验证码为：" + code + "(有效期为一分钟)</p>", true);
        //设置邮件主题名
        helper.setSubject("FlowerPotNet验证码----验证码");
        //发给谁-》邮箱地址
        helper.setTo(email);
        //谁发的-》发送人邮箱
        helper.setFrom("2463252763@qq.com");
        //将邮箱验证码以邮件地址为key存入redis,1分钟过期
        redisTemplate.opsForValue().set(email, code, Duration.ofMinutes(1));////opsForValue() 是 RedisTemplate 提供的一个方法，用于获取用于操作 Redis 字符串类型数据的 ValueOperations 对象
        mailSender.send(mimeMessage);
        return true;
    }
    public String getStoredCode(String email) {
        return redisTemplate.opsForValue().get(email);
    }
}
