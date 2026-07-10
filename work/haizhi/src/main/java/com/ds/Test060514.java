package com.ds;

import java.nio.charset.StandardCharsets;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

/**
 * @author ds
 * @date 2026/5/14
 * @description
 */
public class Test060514 {
    public static void main(String[] args) {
        // 密钥必须是16、24或32字节（对应AES-128、AES-192、AES-256）
        byte[] key = "0123456789ABCDEF0123456789ABCDEF".getBytes(StandardCharsets.UTF_8);

        // 创建AES对象
        AES aes = SecureUtil.aes(key);

        // 原始内容
        String content = "Hello, World! 你好世界！";

        // 加密 - 返回字节数组
        byte[] encryptBytes = aes.encrypt(content);
        System.out.println("加密后(字节): " + HexUtil.encodeHexStr(encryptBytes));

        // 加密 - 返回十六进制字符串
        String encryptHex = aes.encryptHex(content);
        System.out.println("加密后(Hex): " + encryptHex);

        // 加密 - 返回Base64字符串
        String encryptBase64 = aes.encryptBase64(content);
        System.out.println("加密后(Base64): " + encryptBase64);

        // 解密 - 从字节数组解密
        byte[] decryptBytes = aes.decrypt(encryptBytes);
        System.out.println("解密后(字节): " + new String(decryptBytes, StandardCharsets.UTF_8));

        // 解密 - 从十六进制字符串解密
        String decryptFromHex = aes.decryptStr(encryptHex);
        System.out.println("解密后(Hex): " + decryptFromHex);

        // 解密 - 从Base64字符串解密
        String decryptFromBase64 = aes.decryptStr(encryptBase64);
        System.out.println("解密后(Base64): " + decryptFromBase64);
    }
}
