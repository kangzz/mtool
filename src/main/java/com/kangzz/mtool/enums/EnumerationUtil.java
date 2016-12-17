package com.kangzz.mtool.enums;

import com.kangzz.mtool.util.ObjectUtil;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.iterators.EnumerationIterator;

import java.util.Enumeration;

/**
 * 描述：枚举工具类
 * 作者 ：kangzz
 * 日期 ：2016-12-17 10:17:02
 */
public final class EnumerationUtil {

    /** Don't let anyone instantiate this class. */
    private EnumerationUtil(){
    }
    /**
     * 描述：判断枚举中是否存在某个值
     * 作者 ：kangzz
     * 日期 ：2016-12-17 10:18:18
     */
    public static <O> boolean contains(Enumeration<O> enumeration,O value){
        return ObjectUtil.isNullOrEmpty(enumeration) ? false : IteratorUtils.contains(new EnumerationIterator<O>(enumeration), value);
    }
}
