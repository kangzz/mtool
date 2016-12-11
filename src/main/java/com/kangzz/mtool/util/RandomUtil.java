/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kangzz.mtool.util;


import com.kangzz.mtool.exception.UtilException;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;

/**
 * 描述：随机工具类
 * 作者 ：kangzz
 * 日期 ：2016-12-09 23:03:48
 */
public final class RandomUtil{

    public static final Random JVM_RANDOM = new Random();
    private RandomUtil(){
    }
    //  定义所有的字符组成的串
    public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //  定义所有的小写字符组成的串（不包括数字）
    public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //  定义所有的数字字符组成的串
    public static final String numberChar = "0123456789";

    /**
     * 产生长度为length的随机字符串（包括字母和数字）
     * @param length
     * @return
     */
    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(allChar.charAt(JVM_RANDOM.nextInt(allChar.length())));
        }
        return sb.toString();
    }
    /**
     * 产生长度为length的随机字符串（包括字母，不包括数字）
     * @param length
     * @return
     */
    public static String generateMixString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(letterChar.charAt(JVM_RANDOM.nextInt(letterChar.length())));
        }
        return sb.toString();
    }
    /**
     * 产生长度为length的随机小写字符串（包括字母，不包括数字）
     * @param length
     * @return
     */
    public static String generateLowerString(int length) {
        return generateMixString(length).toLowerCase();
    }
    /**
     * 产生长度为length的随机大写字符串（包括字母，不包括数字）
     * @param length
     * @return
     */
    public static String generateUpperString(int length) {
        return generateMixString(length).toUpperCase();
    }
    /**
     * 产生长度为length的'0'串
     * @param length
     * @return
     */
    public static String generateNumberString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(numberChar.charAt(JVM_RANDOM.nextInt(numberChar.length())));
        }
        return sb.toString();
    }
    /**
     * 产生长度为length的'0'串
     * @param length
     * @return
     */
    public static String generateZeroString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append('0');
        }
        return sb.toString();
    }
    /**
     * 将输入的long整数值补全成为fixedLength长度的字符串
     * @param num 数值
     * @param fixedLength 补全总长度
     * @return 前端补0后的字符串
     */
    public static String toFixedLengthString(Number num, int fixedLength) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixedLength - strNum.length() >= 0) {
            sb.append(generateZeroString(fixedLength - strNum.length()));
        } else {
            throw new UtilException("将数字" + num + "转化为长度为" + fixedLength + "的字符串发生异常!");
        }
        sb.append(strNum);
        return sb.toString();
    }
    /**
     * 描述：获取指定长度的数据
     * 作者 ：kangzz
     * 日期 ：2016-12-09 22:32:45
     */
    public static long getRandomWithLength(int length){
        long num = 1;
        for (int i = 0; i < length; ++i){
            num = num * 10;
        }
        // 该值大于等于 0.0 且小于 1.0 正号的 double 值
        double random = JVM_RANDOM.nextDouble();
        random = random < 0.1 ? random + 0.1 : random;// 可能出现 0.09346924349151808
        return (long) (random * num);
    }
    /**
     * 描述：获取字符串内指定的随机长度
     * 作者 ：kangzz
     * 日期 ：2016-12-09 22:33:13
     */
    public static String getRandomFromString(String str,int length){
        return RandomStringUtils.random(length, str);
    }


}