package com.kangzz.mtool.secure;


import com.kangzz.mtool.convert.ConvertException;
import com.kangzz.mtool.lang.Console;
import com.kangzz.mtool.secure.impl.AesSecure;
import com.kangzz.mtool.secure.impl.DigestSecure;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 加密算法登记
 * 
 * @author Looly
 *
 */
public class SecureRegistry {

	/** 默认类型转换器 */
	private Map<String, Secure<?>> defaultSecure;
	/** 用户自定义类型转换器 */
	private Map<String, Secure<?>> customSecure;

	/** 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载 */
	private static class SingletonHolder {
		/** 静态初始化器，由JVM来保证线程安全 */
		private static SecureRegistry instance = new SecureRegistry();
	}
	/**
	 * 获得单例的 {@link SecureRegistry}
	 * @return {@link SecureRegistry}
	 */
	public static SecureRegistry getInstance(){
		return SingletonHolder.instance;
	}

	public SecureRegistry() {
		registerDefaultConverter();
	}
	/**
	 * 登记自定义转换器
	 * 
	 * @param secure 转换器
	 * @return {@link SecureRegistry}
	 */
	public SecureRegistry registerCustom(String secureType, Secure<?> secure) {
		if(null == customSecure){
			synchronized (this) {
				if(null == customSecure){
					customSecure = new ConcurrentHashMap();
				}
			}
		}
		customSecure.put(secureType, secure);
		return this;
	}
	/**
	 * 获得加密算法
	 * @param <T>
	 * 
	 * @param type 类型
	 * @return 转换器
	 */
	@SuppressWarnings("unchecked")
	public <T> Secure<T> getSecure(String type) {
		return (Secure<T>) defaultSecure.get(type);
	}
	/**
	 * 获得自定义转换器
	 * @param <T>
	 * 
	 * @param type 类型
	 * @return 转换器
	 */
	@SuppressWarnings("unchecked")
	public <T> Secure<T> getCustomConverter(String type) {
		return (null == customSecure) ? null : (Secure<T>)customSecure.get(type);
	}
	public byte[] encrypt(DigestUtil.Algorithm algorithm, byte[] data) {
		Secure secure = getSecure(algorithm.getValue());
		if (null == secure) {
			throw new ConvertException("No Secure for type [{}]", algorithm.getValue());
		}
		return secure.encrypt(data);
	}
	public File encrypt(DigestUtil.Algorithm algorithm, File file){
		Secure secure = getSecure(algorithm.getValue());
		if (null == secure) {
			throw new ConvertException("No Secure for type [{}]", algorithm.getValue());
		}
		return secure.encrypt(file);
	}
	public byte[] encrypt(SecureUtil.OrderSecure secureType, byte[] data, String key) {
		Secure secure = getSecure(secureType.getValue());
		if (null == secure) {
			throw new ConvertException("No Secure for type [{}]", secureType.getValue());
		}
		return secure.encrypt(data, key);
	}
	public File encrypt(SecureUtil.OrderSecure secureType,File file, String key){
		Secure secure = getSecure(secureType.getValue());
		if (null == secure) {
			throw new ConvertException("No Secure for type [{}]", secureType.getValue());
		}
		return secure.encrypt(file,key);
	}
	public byte[] decrypt(SecureUtil.OrderSecure secureType, byte[] data, String key) {
		Secure secure = getSecure(secureType.getValue());
		if (null == secure) {
			throw new ConvertException("No Secure for type [{}]", secureType.getValue());
		}
		return secure.decrypt(data, key);
	}
	public File decrypt(SecureUtil.OrderSecure secureType, File file, String key) {
		Secure secure = getSecure(secureType.getValue());
		if (null == secure) {
			throw new ConvertException("No Secure for type [{}]", secureType.getValue());
		}
		return secure.decrypt(file, key);
	}

	//----------------------------------------------------------- Private method start
	/**
	 * 注册默认转换器
	 * @return 转换器
	 */
	private SecureRegistry registerDefaultConverter() {
		defaultSecure = new ConcurrentHashMap();
		//原始类型转换器
		defaultSecure.put(DigestUtil.Algorithm.MD5.getValue(), new DigestSecure(DigestUtil.Algorithm.MD5.getValue()));
		defaultSecure.put(DigestUtil.Algorithm.SHA1.getValue(), new DigestSecure(DigestUtil.Algorithm.SHA1.getValue()));
		defaultSecure.put(DigestUtil.Algorithm.SHA256.getValue(), new DigestSecure(DigestUtil.Algorithm.SHA256.getValue()));
		defaultSecure.put(DigestUtil.Algorithm.SHA348.getValue(), new DigestSecure(DigestUtil.Algorithm.SHA348.getValue()));
		defaultSecure.put(DigestUtil.Algorithm.SHA512.getValue(), new DigestSecure(DigestUtil.Algorithm.SHA512.getValue()));

		defaultSecure.put("AES", new AesSecure());
		return this;
	}
	//----------------------------------------------------------- Private method end
}
