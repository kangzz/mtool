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
            if(!checkBooleanList.get(i)){
                return false;
            }
        }
        return true;
    }
    /**
     * 描述：&& 封装
     * 作者 ：kangzz
     * 日期 ：2016-12-26 18:40:03
     */
    public static Boolean and(boolean... checkBooleans){
        if(checkBooleans == null) {
            return false;
        } else if(checkBooleans.length == 0) {
            return false;
        } else {
            boolean[] arr = checkBooleans;
            int len = checkBooleans.length;
            for (int i = 0; i < len; i++) {
                if(!arr[i]) {
                    return false;
                }
            }
            return true;
        }
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
            if(checkBooleanList.get(i)){
                return true;
            }
        }
        return false;
    }
    /**
     * 描述：|| 封装
     * 作者 ：kangzz
     * 日期 ：2016-12-26 18:40:30
     */
    public static Boolean or(boolean... checkBooleans){
        if(checkBooleans == null) {
            return false;
        } else if(checkBooleans.length == 0) {
            return false;
        } else {
            boolean[] arr = checkBooleans;
            int len = checkBooleans.length;
            for (int i = 0; i < len; i++) {
                if(arr[i]) {
                    return true;
                }
            }
            return false;
        }
    }

}
