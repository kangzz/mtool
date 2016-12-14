package com.kangzz.mtool.convert;

/**
 * 描述：转换接口
 * 作者 ：kangzz
 * 日期 ：2016-12-14 15:28:49
 */
public interface Converter<T> {
	
	/**
	 * 转换为指定类型<br>
	 * 转换为默认值的类型
	 * 
	 * @param value 原始值
	 * @param defaultValue 默认值
	 * @return 转换后的值
	 * @throws IllegalArgumentException 默认值
	 */
	public T convert(Object value, T defaultValue) throws IllegalArgumentException;

}