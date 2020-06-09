package cn.tycoding.common.properties;

import lombok.Data;

/**
 * @author tycoding
 * @date 2020/6/9
 */
@Data
public class ShiroProperties {

    private long sessionTimeout;
    private int cookieTimeout;
    private String anonUrl;
    private String loginUrl;
    private String successUrl;
    private String logoutUrl;
    private String cipherKey;
}
