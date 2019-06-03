package com.example.server.soa.common.constants;

/**
 * @date 2017-10-01
 */
public interface Constants {
    /**
     * 客户端类型
     */
    String CLIENT_APPLE = "apple";
    String CLIENT_IPAD = "iPad";
    String CLIENT_ANDROID = "android";
    String CLIENT_ANDROIDTV = "androidTv";
    String CLIENT_ANDROIDPAD = "androidPad";
    String CLIENT_WEAPP = "weapp";
    String CLIENT_M = "m";
    String CLIENT_PC = "pc";

    /**
     * 网络类型
     */
    String NETWORKTYPE_WIFI = "wifi";
    String NETWORKTYPE_2G = "2g";
    String NETWORKTYPE_3G = "3g";
    String NETWORKTYPE_2G_3G = "2g,3g";
    String NETWORKTYPE_4G = "4g";
    String NETWORKTYPE_5G = "5g";
    String NETWORKTYPE_UNKNOWN = "unknown";

    /**
     * 符号
     */
    String COLON = ":";
    String COMMA = ",";
    String SEMICOLON = ";";
    String HYPHEN = "-";

    /**
     * 用户名正则 4到16位字母数字下划线减号
     */
    String PATTERN_USERNAME = "^[-_a-zA-Z0-9]{4,16}$";
}
