package com.kangzz.mtool.util;

import java.util.Arrays;
import java.util.List;

/**
 * 描述：Boolean判断封装
 * 作者 ：kangzz
 * 日期 ：2016-12-26 13:23:34
 */
public class BooleanUtils {
    /**
     * 描述：所有判断必须是true 返回true 相当于 &&
     * 作者 ：kangzz
     * 日期 ：2016-12-26 13:22:41
     */
    public static Boolean isAllTrue(Boolean... checkBooleans){
        List<Boolean> checkBooleanList = (checkBooleans != null ? Arrays.asList(checkBooleans) : null);
        for (int i = 0; CollectionUtil.isNotEmpty(checkBooleanList) && i < checkBooleanList.size(); i++) {
            if(!checkBooleanList.get(i)){
                return false;
            }
        }
        return true;
    }
    /**
     * 描述：只要有一个为true 返回true 相当于 ||
     * 作者 ：kangzz
     * 日期 ：2016-12-26 13:23:00
     */
    public static Boolean hasTrue(Boolean... checkBooleans){
        List<Boolean> checkBooleanList = (checkBooleans != null ? Arrays.asList(checkBooleans) : null);
        for (int i = 0; CollectionUtil.isNotEmpty(checkBooleanList) && i < checkBooleanList.size(); i++) {
            if(checkBooleanList.get(i)){
                return true;
            }
        }
        return false;
    }

}
