package com.kangzz.mtool.convert.impl;


import com.kangzz.mtool.convert.AbstractConverter;

/**
 * 字符串转换器
 * @author Looly
 *
 */
public class StringConverter extends AbstractConverter<String> {

	@Override
	protected String convertInternal(Object value) {
		return convertToStr(value);
	}

}
