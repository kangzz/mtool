package com.kangzz.mtool.convert.impl;


import com.kangzz.mtool.convert.AbstractConverter;

import java.net.URL;

/**
 * 字符串转换器
 * @author Looly
 *
 */
public class URLConverter extends AbstractConverter<URL> {

	@Override
	protected URL convertInternal(Object value) {
		try {
			return new URL(convertToStr(value));
		} catch (Exception e) {
			// Ignore Exception
		}
		return null;
	}

}
