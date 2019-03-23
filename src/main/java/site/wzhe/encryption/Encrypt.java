package site.wzhe.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2018/7/30.
 */
public class Encrypt {
    public static String MD5encrypt(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] md5Bytes = messageDigest.digest();
            return getHexValue(md5Bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getHexValue(byte[] bytes) {
        StringBuilder hexValue = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int val = ((int) bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) {
        System.out.println(MD5encrypt("wangzhe").length());
    }
}
