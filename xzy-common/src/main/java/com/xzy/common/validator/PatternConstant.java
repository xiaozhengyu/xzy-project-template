package com.xzy.common.validator;

/**
 * 正则表达式常量
 */
public class PatternConstant {

    /**
     * 手机号码
     */
    public static final String MOBILE = "^1\\d{10}$";
    /**
     * IP地址
     */
    public static final String IP = "^((25[0-5]|2[0-4]\\d|[1]\\d{2}|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|[1]\\d{2}|[1-9]?\\d)$";
    /**
     * MAC地址
     */
    public static final String MAC = "^([0-9A-Fa-f]{2}-?){5}([0-9A-Fa-f]{2})$";
    /**
     * 主机名
     */
    public static final String HOST_NAME = "^(?!-)[A-Za-z0-9-]{1,15}(?<!-)$";

    /**
     * url
     */

    public static final String URL = "^(http|https)://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";

    /**
     * 主机名前缀 只能包含字母、数字、下划线
     */
    public static final String HOSTNAME_PREFIX = "^[a-zA-Z0-9-]+$";
}