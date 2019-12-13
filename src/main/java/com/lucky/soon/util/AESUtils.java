package com.lucky.soon.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * 密钥配置在配置文件中即可
 * AES128 算法，加密模式为ECB
 */
public class AESUtils {
    private static Logger log = LoggerFactory.getLogger(AESUtils.class);

    /**
     * @return byte[]
     * @Description 加密，被下面的encryptString调用,如果content为null，不影响方法执行
     * @Date 11:20 2019/7/8
     * @Param [content, key][待加密内容,加密密钥]
     */
    public static byte[] encrypt(String content, String key) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //设置安全随机数
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(key.getBytes());
			keyGenerator.init(128, random);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("UTF-8");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.error("content is null,please check it");
        }
        return null;
    }


    /**
     * @return byte[]
     * @Author K1080068
     * @Description 解密，被下面的decryptString调用,如果content为null，不影响方法执行
     * @Date 11:20 2019/7/8
     * @Param [content, key][待解密内容,解密密钥]
     **/

    public static byte[] decrypt(byte[] content, String key) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(key.getBytes());
			keyGenerator.init(128, random);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.error("content is null,please check it");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return byte[]
     * @Author K1080068
     * @Description 加密，被下面的encryptString调用
     * @Date 11:20 2019/7/8
     * @Param [content, key][待加密内容,加密密钥]
     **/
    public static String encryptString(String content, String key) {
        byte[] s = encrypt(content, key);
        try {
            if (s != null) {
                return parseByte2HexStr(s);
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    /**
     * 字符串解密，解密时调用此方法
     *
     * @param content 要解密的字符串
     * @param key     解密的AES Key
     * @return
     */
    public static String decryptString(String content, String key) {
        byte[] decryptFrom = null;
        byte[] decryptResult = null;
        if (StringUtils.hasText(content) && StringUtils.hasText(key)) {
            decryptFrom = parseHexStr2Byte(content);
            decryptResult = decrypt(decryptFrom, key);
            if (decryptResult != null) {
                return new String(decryptResult);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

}