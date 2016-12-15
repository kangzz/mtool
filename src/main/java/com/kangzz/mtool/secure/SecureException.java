package com.kangzz.mtool.secure;


import com.kangzz.mtool.exception.ExceptionUtil;
import com.kangzz.mtool.util.StrUtil;

import java.io.Serializable;

/**
 * 描述：异常
 * 作者 ：kangzz
 * 日期 ：2016-12-15 12:24:52
 */
public class SecureException extends RuntimeException{

	private static final long serialVersionUID = -6797673388910330387L;

	public SecureException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public SecureException(String message) {
		super(message);
	}

	public SecureException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}

	public SecureException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public SecureException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}
}
