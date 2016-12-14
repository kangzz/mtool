package com.kangzz.mtool.convert.impl;

import com.kangzz.mtool.convert.AbstractConverter;
import com.kangzz.mtool.util.StrUtil;

/**
 * 字符转换器
 * @author Looly
 *
 */
public class CharacterConverter extends AbstractConverter<Character> {

	@Override
	protected Character convertInternal(Object value) {
		if(char.class == value.getClass()){
			return (Character)value;
		}else{
			final String valueStr = convertToStr(value);
			if (StrUtil.isNotBlank(valueStr)) {
				try {
					return Character.valueOf(valueStr.charAt(0));
				} catch (Exception e) {
					//Ignore Exception
				}
			}
		}
		return null;
	}

}
