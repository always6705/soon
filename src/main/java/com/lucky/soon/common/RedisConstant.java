package com.lucky.soon.common;

/**
 * 常量
 * @author dolyw.com
 * @date 2018/9/3 16:03
 */
public class RedisConstant {

    /**
     * redis-OK
     */
    public final static String OK = "OK";

    /**
     * redis过期时间，以秒为单位，一分钟
     */
    public final static int EXRP_MINUTE = 60;

    /**
     * redis过期时间，以秒为单位，一小时
     */
    public final static int EXRP_HOUR = 60 * 60;

    /**
     * redis过期时间，以秒为单位，一天
     */
    public final static int EXRP_DAY = 60 * 60 * 24;

    /**
     * redis-key-前缀-shiro:cache:
     */
    public final static String PREFIX_SHIRO_CACHE = "shiro:cache:";

    /**
     * redis-key-前缀-shiro:access_token:
     */
    public final static String PREFIX_SHIRO_ACCESS_TOKEN = "shiro:access_token:";

    /**
     * redis-key-前缀-shiro:refresh_token:
     */
    public final static String PREFIX_SHIRO_REFRESH_TOKEN = "shiro:refresh_token:";

    /**
     * JWT-account:
     */
    public final static String ACCOUNT = "account";

    /**
     * JWT-currentTimeMillis:
     */
    public final static String CURRENT_TIME_MILLIS = "currentTimeMillis";

    /**
     * PASSWORD_MAX_LEN
     */
    public static final Integer PASSWORD_MAX_LEN = 8;

    /**
     * NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key
     * 	 if it already exist.
     */
    public static final String NX = "NX";

    /**
     * NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key
     * 	 if it already exist.
     */
    public static final String XX = "XX";

    /**
     * EX|PX, expire time units: EX = seconds; PX = milliseconds
     */
    public static final String EX = "EX";

    /**
     * EX|PX, expire time units: EX = seconds; PX = milliseconds
     */
    public static final String PX = "PX";

}
