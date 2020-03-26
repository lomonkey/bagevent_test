package com.test.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;

import java.util.UUID;


/**
 * description       ：
 *
 * @author ：lvhan
 * @version ：1.0
 * @date ：2020/3/26 9:45
 */
public class MD5Utils {

    private MD5Utils(){}

    /**
     * 加密密码
     * @param password password
     * @return 加密后的密码
     */
    public static String encodeMd5(String password) {
        if (password == null) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }


    /**
     * token 加密
     * @param string string
     * @return String
     */
    public static String generateToken(String string) {
        if (StringUtils.isBlank(string)) {
            return null;
        }
        return Base64Utils.encodeToString(string.getBytes());
    }

    public static String parseToken(String string) {
        if (StringUtils.isBlank(string)) {
            return null;
        }
        return new String(Base64Utils.decodeFromString(string));
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
