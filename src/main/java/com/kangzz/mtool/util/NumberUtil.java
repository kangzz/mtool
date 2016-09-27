package com.kangzz.mtool.util;

import org.apache.commons.lang.math.NumberUtils;

/**
 * 描述：数字处理util
 * 作者 ：kangzz
 * 日期 ：2016-09-27 11:53:48
 */
public class NumberUtil extends NumberUtils{
    /**
     * 描述：判断是否为Double
     * 作者 ：kangzz
     * 日期 ：2016-09-27 12:06:26
     */
    public static Boolean isDouble(String str) {
        try {
            if (StrUtil.isBlank(str)) {
                return false;
            }
            Double.parseDouble(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    /**
     * 描述：判断是否为Integer
     * 作者 ：kangzz
     * 日期 ：2016-09-27 12:06:26
     */
    public static Boolean isInteger(String str) {
        try {
            if (StrUtil.isBlank(str)) {
                return false;
            }
            Integer.parseInt(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    /**
     * 描述：判断是否为Long
     * 作者 ：kangzz
     * 日期 ：2016-09-27 12:06:26
     */
    public static Boolean isLong(String str) {
        try {
            if (StrUtil.isBlank(str)) {
                return false;
            }
            Long.parseLong(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    /**
     * 描述：判断是否为Float
     * 作者 ：kangzz
     * 日期 ：2016-09-27 12:06:26
     */
    public static Boolean isFloat(String str) {
        try {
            if (StrUtil.isBlank(str)) {
                return false;
            }
            Float.parseFloat(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    /**
     * 描述：判断是否为Short
     * 作者 ：kangzz
     * 日期 ：2016-09-27 12:06:26
     */
    public static Boolean isShort(String str) {
        try {
            if (StrUtil.isBlank(str)) {
                return false;
            }
            Short.parseShort(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    /**
     * 描述：判断是否为Byte
     * 作者 ：kangzz
     * 日期 ：2016-09-27 12:06:26
     */
    public static Boolean isByte(String str) {
        try {
            if (StrUtil.isBlank(str)) {
                return false;
            }
            Byte.parseByte(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
