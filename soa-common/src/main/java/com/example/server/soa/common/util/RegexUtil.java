package com.example.server.soa.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式匹配
 *
 * @date 2017-10-01
 */
public class RegexUtil {

    /**
     * Email地址正则
     */
    private static final String PATTERN_EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
    /**
     * 网址URL正则
     */
    private static final String PATTERN_URL = "^((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+";
    /**
     * 国内手机正则
     */
    private static final String PATTERN_MOBILE = "^0?(13|14|15|17|18|19)[0-9]{9}";
    /**
     * IP正则
     */
    private static final String PATTERN_IP = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
    /**
     * 身份证号正则
     */
    private static final String PATTERN_ID_CARD = "\\d{17}[\\d|x]|\\d{15}";

    private RegexUtil() {
    }

    public static boolean isEmail(String str) {
        return match(str, PATTERN_EMAIL);
    }

    public static boolean isUrl(String str) {
        return match(str, PATTERN_URL);
    }

    public static boolean isMobilePhone(String str) {
        str = str.replace("-", "");
        return match(str, PATTERN_MOBILE);
    }

    public static boolean isIp(String str) {
        return match(str, PATTERN_IP);
    }

    public static boolean isIdCard(String str) {
        return match(str, PATTERN_ID_CARD);
    }

    public static boolean match(String str, String regex) {
        if (str != null) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                return true;
            }
        }
        return false;
    }
}
