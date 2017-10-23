package com.kangzz.mtool.util;

import com.kangzz.mtool.log.StaticLog;

/**
 * 描述：调试工具
 * 作者 ：kangzz
 * 日期 ：2017-10-23 17:56:18
 */
public class DebugUtils {

    /**
     * 描述：判断是否开启事物
     * 作者 ：kangzz
     * 日期 ：2017-10-23 17:55:55
     */
    public static boolean transactionActive() {
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            Class tsmClass = contextClassLoader.loadClass("org.springframework.transaction.support.TransactionSynchronizationManager");
            Boolean isActive = (Boolean) tsmClass.getMethod("isActualTransactionActive", null).invoke(null, null);
            return isActive;
        } catch (Exception e) {
            StaticLog.error(e);
        }
        // If we got here it means there was an exception
        throw new IllegalStateException("DebugUtils.transactionActive was unable to complete properly");
    }

}
