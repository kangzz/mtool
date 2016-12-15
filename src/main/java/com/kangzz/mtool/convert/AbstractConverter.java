package com.kangzz.mtool.convert;


import com.kangzz.mtool.util.CollectionUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.MessageFormat;

/**
 * 抽象转换器，提供通用的转换逻辑，同时通过
 * @author Looly
 *
 */
public abstract class AbstractConverter<T> implements Converter<T>{
	
	@Override
	@SuppressWarnings("unchecked")
	public T convert(Object value, T defaultValue) {
		Class<T> targetType = this.getTargetType();
		if(null == targetType){
			targetType = (Class<T>) defaultValue.getClass();
		}
		
		if(targetType.isPrimitive()){
			//原始类型直接调用内部转换，内部转换永远不会返回null
			return this.convertInternal(value);
		}
		
		if(null == value){
			return defaultValue;
		}
		if(null == defaultValue || targetType.isInstance(defaultValue)){
			if(targetType.isInstance(value)){
				//已经是目标类型，不需要转换
				return (T) targetType.cast(value);
			}
			final T convertInternal = this.convertInternal(value);
			return ((null == convertInternal) ? defaultValue : convertInternal);
		}else{
			throw new IllegalArgumentException(MessageFormat.format("Default value [{0}] is not the instance of [{1}]]", defaultValue, targetType));
		}
	}
	
	/**
	 * 内部转换器，被 {@link AbstractConverter#convert(Object, Object)} 调用，实现基本转换逻辑
	 * @param value 值
	 * @return 转换后的类型
	 */
	protected abstract T convertInternal(Object value);
	
	/**
	 * 值转为String
	 * @param value 值
	 * @return String
	 */
	protected String convertToStr(Object value) {
		if (null == value) {
			return null;
		}
		if (value instanceof String) {
			return (String) value;
		}else if (CollectionUtil.isArray(value)) {
			return CollectionUtil.toString(value);
		}
		return value.toString();
	}
	
	/**
	 * 获得此类实现类的泛型类型
	 * @return 此类的泛型类型
	 */
	@SuppressWarnings("unchecked")
	public Class<T> getTargetType() {
		Type superType = getClass().getGenericSuperclass();
		if(superType instanceof ParameterizedType){
			ParameterizedType genericSuperclass = (ParameterizedType) superType;
			Type[] types = genericSuperclass.getActualTypeArguments();
			if(null != types && types.length > 0){
				Type type = types[0];
				if(type instanceof Class){
					return (Class<T>)type;
				}
			}
		}
		return null;
	}
}
