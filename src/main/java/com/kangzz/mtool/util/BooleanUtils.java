package com.kangzz.mtool.util;

import java.util.Arrays;
import java.util.List;

/**
 * 描述：Boolean判断封装
 * 作者 ：kangzz
 * 日期 ：2016-12-26 13:23:34
 */
public class BooleanUtils {

    private BooleanUtils(){

    }
    public static boolean isTrue(Boolean bool) {
        return Boolean.TRUE.equals(bool);
    }

    public static boolean isNotTrue(Boolean bool) {
        return !isTrue(bool);
    }

    public static boolean isFalse(Boolean bool) {
        return Boolean.FALSE.equals(bool);
    }

    public static boolean isNotFalse(Boolean bool) {
        return !isFalse(bool);
    }

    public static boolean toBoolean(Boolean bool) {
        return bool != null && bool.booleanValue();
    }
    public static Boolean toBoolean(boolean bool) {
        return bool;
    }
    /**
     * 描述：&& 封装
     * 作者 ：kangzz
     * 日期 ：2016-12-26 18:39:45
     */
    public static Boolean and(Boolean... checkBooleans){
        List<Boolean> checkBooleanList = (checkBooleans != null ? Arrays.asList(checkBooleans) : null);
        if(CollectionUtil.isNotEmpty(checkBooleanList)){
           return false;
        }
        for (int i = 0;i < checkBooleanList.size(); i++) {
            if(!isTrue(checkBooleanList.get(i))){
                return false;
            }
        }
        return true;
    }
    /**
     * 描述：|| 封装
     * 作者 ：kangzz
     * 日期 ：2016-12-26 18:40:30
     */
    public static Boolean or(Boolean... checkBooleans){
        List<Boolean> checkBooleanList = (checkBooleans != null ? Arrays.asList(checkBooleans) : null);
        if(CollectionUtil.isNotEmpty(checkBooleanList)){
            return false;
        }
        for (int i = 0;i < checkBooleanList.size(); i++) {
            if(isTrue(checkBooleanList.get(i))){
                return true;
            }
        }
        return false;
    }
    /**
     * 描述：全部为null 返回为true
     * 作者 ：kangzz
     * 日期 ：2016-12-26 22:45:36
     */
    public static Boolean andNull(Object... obj){
        if(ObjectUtil.isNull(obj)){
            return true;
        }
        List<Object> objList = Arrays.asList(obj);
        for (int i = 0; i < objList.size(); i++) {
            if(ObjectUtil.isNotNull(objList.get(i))){
                return false;
            }
        }
        return true;
    }
    /**
     * 描述：全部为null或空返回为true
     * 作者 ：kangzz
     * 日期 ：2016-12-26 22:46:27
     */
    public static Boolean andNullOrEmpty(Object... obj){
        if(ObjectUtil.isNull(obj)){
            return true;
        }
        List<Object> objList = Arrays.asList(obj);
        for (int i = 0; i < objList.size(); i++) {
            if(ObjectUtil.isNotNullOrEmpty(objList.get(i))){
                return false;
            }
        }
        return true;
    }
    /**
     * 描述：有一个为null 返回为true
     * 作者 ：kangzz
     * 日期 ：2016-12-26 22:46:45
     */
    public static Boolean orNull(Object... obj){
        if(ObjectUtil.isNull(obj)){
            return true;
        }
        List<Object> objList = Arrays.asList(obj);
        for (int i = 0; i < objList.size(); i++) {
            if(ObjectUtil.isNull(objList.get(i))){
                return true;
            }
        }
        return false;
    }
    /**
     * 描述：有一个为null或者为空 返回为true
     * 作者 ：kangzz
     * 日期 ：2016-12-26 22:47:01
     */
    public static Boolean orNullOrEmpty(Object... obj){
        if(ObjectUtil.isNullOrEmpty(obj)){
            return true;
        }
        List<Object> objList = Arrays.asList(obj);
        for (int i = 0; i < objList.size(); i++) {
            if(ObjectUtil.isNullOrEmpty(objList.get(i))){
                return true;
            }
        }
        return false;
    }
    /**
     * 描述：全部不为null 返回为true
     * 作者 ：kangzz
     * 日期 ：2016-12-26 22:45:36
     */
    public static Boolean andNotNull(Object... obj){
        if(ObjectUtil.isNull(obj)){
            return false;
        }
        List<Object> objList = Arrays.asList(obj);
        for (int i = 0; i < objList.size(); i++) {
            if(ObjectUtil.isNull(objList.get(i))){
                return false;
            }
        }
        return true;
    }
    /**
     * 描述：全部不为null或空 返回为true
     * 作者 ：kangzz
     * 日期 ：2016-12-26 22:46:27
     */
    public static Boolean andNotNullOrEmpty(Object... obj){
        if(ObjectUtil.isNullOrEmpty(obj)){
            return false;
        }
        List<Object> objList = Arrays.asList(obj);
        for (int i = 0; i < objList.size(); i++) {
            if(ObjectUtil.isNullOrEmpty(objList.get(i))){
                return false;
            }
        }
        return true;
    }
    /**
     * 描述：有一个匹配即返回true
     * 作者 ：kangzz
     * 日期 ：2016-12-29 10:36:54
     */
    public static <T> Boolean orEquals(T baseObj, T... obj){
        if(andNull(baseObj,obj)){
            return true;
        }
        if(ObjectUtil.isNull(obj)){
            return false;
        }
        List<T> objList = Arrays.asList(obj);
        for (int i = 0; i < objList.size(); i++) {
            if(ObjectUtil.equals(baseObj,objList.get(i))){
                return true;
            }
        }
        return false;
    }
    /**
     * 描述：相当于三元判断 封装null为false的判断
     * 作者 ：kangzz
     * 日期 ：2016-12-26 20:20:06
     */
    public static <T> T toObjByBoolean(Boolean checkBoolean, T trueValue, T falseValue){
        return toObjByBoolean(checkBoolean,trueValue,falseValue,falseValue);
    }
    /**
     * 描述：如果判断为null 增加为null时定义参数
     * 作者 ：kangzz
     * 日期 ：2016-12-26 20:22:55
     */
    public static <T> T toObjByBoolean(Boolean checkBoolean, T trueValue, T falseValue, T nullValue){
        if(isTrue(checkBoolean)){
            return trueValue;
        }else if(isFalse(checkBoolean)){
            return falseValue;
        }else {
            return nullValue;
        }
    }


}
