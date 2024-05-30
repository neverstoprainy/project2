package com.utils;

import cn.hutool.core.util.IdUtil;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

/**
 * @author zym
 * @version 1.0
 * @description: TODO
 * @date 2024/5/29 23:22
 */
public class CodeGeneratorUtil {

    /**
     * 生成指定长度的验证码
     * @param length 长度
     * @return
     */
    public static String generateCode(int length){
        return UUID.randomUUID().toString().substring(0, length);
    }

    public static String generateBase64Code(int length) {
        String code = generateCode(length);
        return Base64.getEncoder().encodeToString(code.getBytes(StandardCharsets.UTF_8));
    }
}
