package me.smart.order.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
import java.security.SecureRandom;

public class PasswordUtil {

    public static final int SALT_BYTE_SIZE = 32;

    public static final String MD5 = "MD5";
    public static final String SHA_256 = "SHA-256";
    public static final String SHA_512 = "SHA-512";

    /**
     * 生成盐值
     *
     * @return salt
     */
    public static String createSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        return toHex(salt);
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }

    /**
     * @param password 明文
     * @param alg      加密算法
     * @return 密文
     */
    public static String createHash(String password, String alg) {
        String newPass;
        if (MD5.equals(alg)) {
            newPass = DigestUtils.md5Hex(password);
        } else if (SHA_256.equals(alg)) {
            newPass = DigestUtils.sha256Hex(password);
        } else if (SHA_512.equals(alg)) {
            newPass = DigestUtils.sha512Hex(password);
        } else {
            newPass = password;
        }
        return newPass;
    }

    public static void main(String[] args) {
        String salt = PasswordUtil.createSalt();
        System.out.println("salt:" + salt);

    }

}
