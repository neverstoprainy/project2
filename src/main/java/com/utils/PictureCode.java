package com.utils;

/**
 * @author zym
 * @version 1.0
 * @description: 图片验证码
 * @date 2024/5/30 19:11
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.Duration;
import java.util.Base64;
import java.util.Random;

public class PictureCode {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    // 生成长度为4的随机验证码字符串
    public static String generateCaptchaText() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captchaText = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(characters.length());
            captchaText.append(characters.charAt(index));
        }
        return captchaText.toString();
    }
    public static String generateCaptchaId() {
        int length = 8;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captchaId = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            captchaId.append(characters.charAt(index));
        }
        return captchaId.toString();
    }

    // 生成验证码图片，并将其转换为Base64编码的字符串
    public static String generateCaptchaImageBase64(String captchaText) {
        int width = 160;
        int height = 60;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(captchaText, 20, 50);
        g.dispose();

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos);
            byte[] bytes = baos.toByteArray();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate captcha image", e);
        }
    }


}
