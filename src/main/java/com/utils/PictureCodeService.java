package com.utils;

/**
 * @author zym
 * @version 1.0
 * @description: TODO
 * @date 2024/5/30 19:26
 */
import cn.hutool.captcha.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;

@Service
public class PictureCodeService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String generateCaptcha(String email) {
        // 生成验证码文本
        String captchaText = PictureCode.generateCaptchaText();

        // 生成验证码编号
        String captchaId = PictureCode.generateCaptchaId();

        // 将验证码图片转换为Base64编码
        String base64Captcha = PictureCode.generateCaptchaImageBase64(captchaText);

        // 将验证码文本和编号存储在Redis中，设置5分钟的过期时间
        redisTemplate.opsForValue().set(email + "_captcha_id", captchaId, Duration.ofMinutes(5));
        redisTemplate.opsForValue().set(email + "_captcha", captchaText, Duration.ofMinutes(5));

        return base64Captcha;
    }
    public boolean validateCaptcha(String email, String enteredCaptcha) {
        String storedCaptcha = redisTemplate.opsForValue().get(email + "_captcha");
        return storedCaptcha != null && storedCaptcha.equalsIgnoreCase(enteredCaptcha);
    }
}
