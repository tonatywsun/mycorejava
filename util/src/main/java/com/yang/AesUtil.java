package com.yang;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * AES 加密工具类
 *
 * @author tonasun
 */
public class AesUtil {

    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * AES 加密操作
     *
     * @param content  待加密内容
     * @param password 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String password) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            byte[] byteContent = content.getBytes("utf-8");
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            // 通过Base64转码返回
            String data = Base64.encode(result);
            return data.replaceAll("\r|\n", "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @param password
     * @return
     */
    public static String decrypt(String content, String password) {

        try {
            // 实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));
            // 执行操作
            byte[] result = cipher.doFinal(Base64.decode(content));
            return new String(result, "utf-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password) {
        // 生成一个密钥
        try {
            // 转换为AES专用密钥
            return new SecretKeySpec(password.getBytes("utf-8"), KEY_ALGORITHM);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String content = "17621210716";
        System.out.println("加密前：" + content);
        // 16位密钥
        String key = "Ab123456.,;/'[po";
        System.out.println("加密密钥和解密密钥：" + key);
        String encrypt = encrypt(content, key);
        System.out.println("加密后：" + encrypt);
        String decrypt = decrypt(encrypt, key);
        System.out.println("解密后：" + decrypt);
    }
}
