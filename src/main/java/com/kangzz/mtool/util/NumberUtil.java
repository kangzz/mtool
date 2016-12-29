package com.kangzz.mtool.util;

import com.kangzz.mtool.convert.Convert;
import com.kangzz.mtool.exception.UtilException;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 描述：数字处理util
 * 作者 ：kangzz
 * 日期 ：2016-09-27 11:53:48
 */
public class NumberUtil{
    /**
     * 默认精度
     */
    private static final int DEFAULT_SCALE = 2;

    private static final int ZERO = 0;

    public final static int ROUND_UP =           0;

    public final static int ROUND_DOWN =         1;

    public final static int ROUND_CEILING =      2;

    public final static int ROUND_FLOOR =        3;

    public final static int ROUND_HALF_UP =      4;

    public final static int ROUND_HALF_DOWN =    5;

    public final static int ROUND_HALF_EVEN =    6;

    public final static int ROUND_UNNECESSARY =  7;


    /**
     * 描述：判断是否为Double
     * 作者 ：kangzz
     * 日期 ：2016-09-27 12:06:26
     */
    public static Boolean isDouble(Object obj) {
        String str = Convert.toStr(obj,null);
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
    public static Boolean isInteger(Object obj) {
        String str = Convert.toStr(obj,null);
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
    public static Boolean isLong(Object obj) {
        try {
            String str = Convert.toStr(obj,null);
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
    public static Boolean isFloat(Object obj) {
        try {
            String str = Convert.toStr(obj,null);
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
    public static Boolean isShort(Object obj) {
        try {
            String str = Convert.toStr(obj,null);
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
    public static Boolean isByte(Object obj) {
        try {
            String str = Convert.toStr(obj,null);
            if (StrUtil.isBlank(str)) {
                return false;
            }
            Byte.parseByte(str);
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
    public static Boolean isBigDecimal(Object obj) {
        try {
            String str = Convert.toStr(obj,null);
            if (StrUtil.isBlank(str)) {
                return false;
            }
            new BigDecimal(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    /**
     * 描述：判断是否为数字
     * 作者 ：kangzz
     * 日期 ：2016-09-27 12:06:26
     */
    public static Boolean isNumber(Object obj) {
        String str = Convert.toStr(obj,null);
        return NumberUtils.isNumber(str);
    }
    /**
     * 描述：提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     * 作者 ：kangzz
     * 日期 ：2016-09-27 15:38:17
     */
    public static BigDecimal add(Object v1, Object v2) {
        return Convert.toBigDecimal(v1,BigDecimal.ZERO).add(Convert.toBigDecimal(v2,BigDecimal.ZERO));
    }
    /**
     * 描述：提供精确的加法运算。
     * @param v1 被加数
     * @param values 加数
     * @return 多个数据求和
     * 作者 ：kangzz
     * 日期 ：2016-09-27 15:38:17
     */
    public static BigDecimal addAll(Object v1, Object... values) {
        List<Object> valueList = (values != null ? Arrays.asList(values) : null);
        BigDecimal returnValue = Convert.toBigDecimal(v1,BigDecimal.ZERO);
        if(CollectionUtil.isNotEmpty(valueList)){
            return returnValue;
        }
        for (int i = 0; i < valueList.size(); i++) {
            returnValue = returnValue.add(Convert.toBigDecimal(valueList.get(i),BigDecimal.ZERO));
        }
        return returnValue;
    }
    /**
     * 描述：提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     * 作者 ：kangzz
     * 日期 ：2016-09-27 15:38:40
     */
    public static BigDecimal subtract(Object v1, Object v2) {
        BigDecimal bigDecimal_v1 = Convert.toBigDecimal(v1,null);
        if(bigDecimal_v1 == null){
            throw new UtilException(StrUtil.format("NumberUtil [{}] subtract [{}] error!", Convert.toStr(v1,null)),Convert.toStr(v2,null));
        }
        return bigDecimal_v1.subtract(Convert.toBigDecimal(v2,BigDecimal.ZERO));
    }
    /**
     * 描述：减法
     * 作者 ：kangzz
     * 日期 ：2016-12-29 10:12:26
     */
    public static BigDecimal subtractAll(Object v1, Object... values) {
        List<Object> valueList = (values != null ? Arrays.asList(values) : null);
        BigDecimal returnValue = Convert.toBigDecimal(v1,BigDecimal.ZERO);
        if(CollectionUtil.isNotEmpty(valueList)){
            return returnValue;
        }
        for (int i = 0; i < valueList.size(); i++) {
            returnValue = returnValue.subtract(Convert.toBigDecimal(valueList.get(i),BigDecimal.ZERO));
        }
        return returnValue;
    }
    /**
     * 描述：提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     * 作者 ：kangzz
     * 日期 ：2016-09-27 15:39:32
     */
    public static BigDecimal multiply(Object v1, Object v2) {
        BigDecimal bigDecimal_v1 = Convert.toBigDecimal(v1,null);
        BigDecimal bigDecimal_v2 = Convert.toBigDecimal(v2,null);
        if(bigDecimal_v1 == null || bigDecimal_v2 == null){
            throw new UtilException(StrUtil.format("NumberUtil [{}] multiply [{}] error!", Convert.toStr(v1,null)),Convert.toStr(v2,null));
        }
        return bigDecimal_v1.multiply(bigDecimal_v2);
    }
    /**
     * 描述：提供（相对）精确的除法运算，当发生除不尽的情况时，精确到小数点以后2位，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     * 作者 ：kangzz
     * 日期 ：2016-09-27 16:10:10
     */
    public static BigDecimal divide(Object v1, Object v2) {
        return divide(v1, v2, DEFAULT_SCALE,ROUND_HALF_UP);
    }
    /**
     * 描述：提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指定精度
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @param roundType 舍取类型
     * @return 两个参数的商
     * 作者 ：kangzz
     * 日期 ：2016-09-27 16:10:57
     */
    public static BigDecimal divide(Object v1, Object v2, int scale, int roundType) {
        BigDecimal bigDecimal_v1 = Convert.toBigDecimal(v1,null);
        BigDecimal bigDecimal_v2 = Convert.toBigDecimal(v2,null);
        if(bigDecimal_v1 == null || bigDecimal_v2 == null){
            throw new UtilException(StrUtil.format("NumberUtil [{}] divide [{}] error!", Convert.toStr(v1,null)),Convert.toStr(v2,null));
        }
        if(!isCompareToGT(v2,ZERO)){
            throw new UtilException(StrUtil.format("NumberUtil [{}] divide [{}] Dividend must greater than zero error!", Convert.toStr(v1,null)),Convert.toStr(v2,null));
        }
        return setScale(bigDecimal_v1.divide(bigDecimal_v2),scale,roundType);
    }
    /**
     * 描述：比较两个数大小 当v1 大于 v2 返回true 否则返回false
     * @param v1 被比较数
     * @param v2 比较数
     * @return
     * 作者 ：kangzz
     * 日期 ：2016-09-27 16:12:43
     */
    public static Boolean isCompareToGT(Object v1, Object v2) {
        BigDecimal bigDecimal_v1 = Convert.toBigDecimal(v1,null);
        BigDecimal bigDecimal_v2 = Convert.toBigDecimal(v2,null);
        if(bigDecimal_v1 == null || bigDecimal_v2 == null){
            throw new UtilException(StrUtil.format("NumberUtil [{}] isCompareToGT [{}] error!", Convert.toStr(v1,null)),Convert.toStr(v2,null));
        }
        return bigDecimal_v1.compareTo(bigDecimal_v2) > ZERO;
    }
    /**
     * 描述：比较两个数大小 当v1 大于等于 v2 返回true 否则返回false
     * @param v1 被比较数
     * @param v2 比较数
     * @return
     * 作者 ：kangzz
     * 日期 ：2016-09-27 16:12:43
     */
    public static Boolean isCompareToGtOrEqual(Object v1, Object v2) {
        BigDecimal bigDecimal_v1 = Convert.toBigDecimal(v1,null);
        BigDecimal bigDecimal_v2 = Convert.toBigDecimal(v2,null);
        if(bigDecimal_v1 == null || bigDecimal_v2 == null){
            throw new UtilException(StrUtil.format("NumberUtil [{}] isCompareToGT [{}] error!", Convert.toStr(v1,null)),Convert.toStr(v2,null));
        }
        return bigDecimal_v1.compareTo(bigDecimal_v2) >= ZERO;
    }
    /**
     * 描述：默认四色五入保留2位小数
     * 作者 ：kangzz
     * 日期 ：2016-09-27 14:49:52
     */
    public static BigDecimal setScaleDefault(Object object){
        return setScale(object,DEFAULT_SCALE,ROUND_HALF_UP);
    }
    /**
     * 描述：
     * @param object 处理对象
     * @param scale 保留小数位数
     * @param roundType 执行舍取方式
     * @return BigDecimal
     * 作者 ：kangzz
     * 日期 ：2016-09-27 14:51:55
     */
    public static BigDecimal setScale(Object object,int scale,int roundType){
        if(object == null){
            return null;
        }
        BigDecimal value = Convert.toBigDecimal(object,null);
        if(value == null){
            throw new UtilException(StrUtil.format("NumberUtil [{}] to SetScale [{}] roundType [{}] error!", Convert.toStr(object,null)),scale,roundType);
        }
        if(!isCompareToGT(scale,ZERO)){
            throw new UtilException(StrUtil.format("NumberUtil [{}] to SetScale [{}] roundType [{}] scale must greater than zero error!", Convert.toStr(object,null)),scale,roundType);
        }
        return value.setScale(scale,roundType);
    }
    /**
     * 描述：数字金额转换成中文金额
     * @param object 处理对象
     * @return String
     * 作者 ：kangzz
     * 日期 ：2016-09-27 14:51:55
     */
    public static String number2CNMoney(Object object) {
        if(object == null){
            return null;
        }
        Double value = Convert.toDouble(object,null);
        if(value == null){
            throw new UtilException(StrUtil.format("NumberUtil [{}] to china money error!", Convert.toStr(object,null)));
        }
        return Convert.digitUppercase(value);
    }

}
