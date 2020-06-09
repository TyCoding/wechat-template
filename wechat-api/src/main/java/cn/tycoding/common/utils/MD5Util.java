package cn.tycoding.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/**
 * Shiro MD5 加密
 *
 * @author tycoding
 * @date 2020/6/9
 */
@Component
public class MD5Util {

    // 算法名称
    private static final String ALGORITH_NAME = "MD5";
    // 迭代次数
    private static final int HASH_ITERATIONS = 2;

    //加密算法
    public static String encryptPassword(String username, String password) {
        if (username == null || password == null) {
            return null;
        }
        return new SimpleHash(
                ALGORITH_NAME,
                StringUtils.lowerCase(StringUtils.lowerCase(password)),
                ByteSource.Util.bytes(StringUtils.lowerCase(username)),
                HASH_ITERATIONS).toHex();
    }
}
