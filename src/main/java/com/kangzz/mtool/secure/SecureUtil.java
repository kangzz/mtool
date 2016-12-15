package com.kangzz.mtool.secure;

import com.kangzz.mtool.util.*;
import java.io.File;
import java.util.UUID;


/**
 * 安全相关工具类
 * 
 * @author xiaoleilu
 *
 */
public class SecureUtil {


	public static enum Algorithm {
		MD5("AES"), SHA256("DES"), SHA348("SHA-348"), SHA512("SHA-512"), SHA1("SHA-1");

		private String value;

		private Algorithm(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	//------------------------------------------------------------------- MD5

	/**
	 * MD5算法加密
	 * @param data 被加密的字符串
	 * @param charset 字符集
	 * @return 被加密后的字符串
	 * @see DigestUtil#md5Hex(String, String)
	 */
	public static String md5Hex(String data, String charset) {
		return HexUtil.encodeHexStr(md5(data, charset));
	}
	/**
	 * MD5算法加密
	 *
	 * @param data 被加密的字符串
	 * @return 被加密后的字符串
	 * @see DigestUtil#md5Hex(String, String)
	 */
	public static String md5(String data) {
		return md5Hex(data, CharsetUtil.UTF_8);
	}
	/**
	 * MD5算法加密
	 *
	 * @param data 被加密的字符串
	 * @return 被加密后的字符串
	 * @see DigestUtil#md5Hex(byte[])
	 */
	public static byte[] md5(byte[] data) {
		return encrypt(DigestUtil.Algorithm.MD5, data);
	}
	/**
	 * MD5算法加密
	 *
	 * @param file 被加密的文件
	 * @return 被加密后的字符串
	 * @see DigestUtil#md5Hex(File)
	 */
	public static File md5(File file) {
		return encrypt(DigestUtil.Algorithm.MD5, file);
	}

	/**
	 * MD5算法加密
	 * @param data 被加密的字符串
	 * @param charset 字符集
	 * @return 被加密后的字符串
	 * @see DigestUtil#md5Hex(String, String)
	 */
	private static byte[] md5(String data, String charset) {
		return md5(StrUtil.bytes(data, charset));
	}
	/**
	 * SHA-1算法加密
	 *
	 * @param data 被加密的字符串
	 * @param charset 字符集
	 * @return 被加密后的字符串
	 */
	public static String sha1(String data, String charset) {
		return HexUtil.encodeHexStr(encrypt(DigestUtil.Algorithm.SHA1,StrUtil.bytes(data, charset)));
	}
	/**
	 * SHA-1算法加密
	 *
	 * @param file 被加密的字符串
	 * @return 被加密后的字符串
	 */
	public static File sha1(File file) {
		return encrypt(DigestUtil.Algorithm.SHA1,file);
	}

	public static byte[] encrypt(DigestUtil.Algorithm algorithm, byte[] data){
		return SecureRegistry.getInstance().encrypt(algorithm,data);
	}
	public static File encrypt(DigestUtil.Algorithm algorithm, File file){
		return SecureRegistry.getInstance().encrypt(algorithm,file);
	}








	public static byte[] decrypt(String secureType, byte[] data, String key){
		return SecureRegistry.getInstance().encrypt(secureType,data, key);
	}
	public static File decrypt(String secureType, File file, String key){
		return SecureRegistry.getInstance().encrypt(secureType, file, key);
	}
	/**
	 * @return 简化的UUID，去掉了横线
	 */
	public static String simpleUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}

}
