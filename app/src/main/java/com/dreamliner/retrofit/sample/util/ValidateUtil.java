package com.dreamliner.retrofit.sample.util;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author chenzj
 * @Title: ValidateUtil
 * @Description: 类的描述 -
 * @date 2016/12/26 15:23
 * @email admin@chenzhongjin.cn
 */
public class ValidateUtil {

    public final static int PHONE_NUM = 0;
    public final static int CHINESE = 1;
    public final static int EMAIL = 2;
    public final static int PASSWD = 3;

    private static Pattern emailPattern;
    private static Pattern phonePattern;
    private static Pattern yidongPattern;
    private static Pattern liantongPattern;
    private static Pattern dianxinPattern;
    private static Pattern passwordPattern;
    private static Pattern chinesePattern;

    /**
     * chenzj 是否邮箱
     */
    private static boolean isEmail(String email) {
        if (null == emailPattern)
            initEmailPattern();
        return emailPattern.matcher(email).matches();
    }

    private static void initEmailPattern() {
        emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    }

    /**
     * chenzj 是否手机号码 - 简单校验1开头.11位数字
     */
    private static boolean isPhoneNumber(String phoneNumber) {
        if (null == phonePattern || null == yidongPattern || null == liantongPattern || null == dianxinPattern)
            initPhonePatter();
        return phonePattern.matcher(phoneNumber).matches();
        /*
        && (yidongPattern.matcher(phoneNumber).matches()
                || liantongPattern.matcher(phoneNumber).matches() || dianxinPattern.matcher(phoneNumber).matches());
                */
    }

    private static void initPhonePatter() {
        phonePattern = Pattern.compile("^1\\d{10}$");
        /*
        yidongPattern = Pattern.compile("^1((34|35|36|37|38|39|47|50|51|52|57|58|59|78|82|83|84|87|88)[0-9]|705)\\d{3,7}$");
        liantongPattern = Pattern.compile("^1((30|31|32|45|55|56|76|85|86)[0-9]|709)\\d{3,7}$");
        dianxinPattern = Pattern.compile("^1((33|53|80|81|89|77|73)[0-9]|700)\\d{3,7}$");
        */
    }

    /**
     * chenzj 是否密码 6-12位的字母+数字+基本符号
     */
    private static boolean isPassword(String password) {
        if (passwordPattern == null)
            initPasswordPatter();
        return passwordPattern.matcher(password).matches();
    }

    private static void initPasswordPatter() {
        passwordPattern = Pattern.compile("^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{6,12}$");
    }

    /**
     * chenzj 是否中文字符串
     */
    private static boolean isChinese(String str) {
        if (null == chinesePattern)
            initChinese();
        return chinesePattern.matcher(str).matches();
    }

    private static void initChinese() {
        chinesePattern = Pattern.compile("[\u4e00-\u9fa5]+");
    }

    /**
     * chenzj 是否空对象
     */
    public static boolean isValidate(Object obj) {
        return obj != null;
    }

    /**
     * chenzj 是否空集合
     */
    public static boolean isValidate(Collection<?> collection) {
        return collection != null && collection.size() > 0;
    }

    /**
     * chenzj 是否空map
     */
    public static boolean isValidate(Map<?, ?> map) {
        return map != null && map.size() > 0;
    }

    public static boolean isValidate(int type, String str) {
        switch (type) {
            case PHONE_NUM:
                return isPhoneNumber(str);
            case CHINESE:
                return isChinese(str);
            case EMAIL:
                return isEmail(str);
            case PASSWD:
                return isPassword(str);
        }
        return false;
    }

}
