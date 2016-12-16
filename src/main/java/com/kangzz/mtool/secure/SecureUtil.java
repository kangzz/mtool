package com.kangzz.mtool.secure;

import com.kangzz.mtool.util.CharsetUtil;
import com.kangzz.mtool.util.HexUtil;
import com.kangzz.mtool.util.StrUtil;

import java.io.File;
import java.util.UUID;


/**
 * 描述：加密解密
 * 作者 ：kangzz
 * 日期 ：2016-12-16 10:08:23
 */
public class SecureUtil {

	/**
	 * 描述：对称加密
	 * 作者 ：kangzz
	 * 日期 ：2016-12-16 10:37:52
	 */
	public static enum Symmetric {
		AES("AES"), DES("DES");

		private String value;

		private Symmetric(String value) {
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


	/**
	 * 描述：AES加密字符串
	 * 作者 ：kangzz
	 * 日期 ：2016-12-16 10:22:09
	 */
	public static String encryptAES(String data, String key) {
		return HexUtil.parseByte2HexStr(encrypt(Symmetric.AES,StrUtil.bytes(data, CharsetUtil.UTF_8),key));
	}
	/**
	 * 描述：AES解密字符串
	 * 作者 ：kangzz
	 * 日期 ：2016-12-16 10:22:09
	 */
	public static String decryptAES(String data, String key) {
		return new String(decrypt(Symmetric.AES,HexUtil.parseHexStr2Byte(data),key));
	}
	/**
	 * 描述：AES加密文件
	 * 作者 ：kangzz
	 * 日期 ：2016-12-16 10:22:39
	 */
	public static File encryptAES(File file, String key){
		return encrypt(Symmetric.AES, file, key);
	}
	/**
	 * 描述：AES解密文件
	 * 作者 ：kangzz
	 * 日期 ：2016-12-16 10:22:56
	 */
	public static File decryptAES(File file, String key){
		return decrypt(Symmetric.AES, file, key);
	}
	/**
	 * 描述：DES加密字符串
	 * 作者 ：kangzz
	 * 日期 ：2016-12-16 10:22:09
	 */
	public static String encryptDES(String data, String key) {
		return HexUtil.parseByte2HexStr(encrypt(Symmetric.DES,StrUtil.bytes(data, CharsetUtil.UTF_8),key));
	}
	/**
	 * 描述：DES解密字符串
	 * 作者 ：kangzz
	 * 日期 ：2016-12-16 10:22:09
	 */
	public static String decryptDES(String data, String key) {
		return new String(decrypt(Symmetric.DES,HexUtil.parseHexStr2Byte(data),key));
	}
	/**
	 * 描述：DES加密文件
	 * 作者 ：kangzz
	 * 日期 ：2016-12-16 10:22:39
	 */
	public static File encryptDES(File file, String key){
		return encrypt(Symmetric.DES, file, key);
	}
	/**
	 * 描述：DES解密文件
	 * 作者 ：kangzz
	 * 日期 ：2016-12-16 10:22:56
	 */
	public static File decryptDES(File file, String key){
		return decrypt(Symmetric.DES, file, key);
	}


	/**摘要算法*/
	public static byte[] encrypt(DigestUtil.Algorithm algorithm, byte[] data){
		return SecureRegistry.getInstance().encrypt(algorithm,data);
	}
	public static File encrypt(DigestUtil.Algorithm algorithm, File file){
		return SecureRegistry.getInstance().encrypt(algorithm,file);
	}
	/**对称加密*/
	public static byte[] encrypt(Symmetric sourceType, byte[] data, String key){
		return SecureRegistry.getInstance().encrypt(sourceType,data,key);
	}
	public static File encrypt(Symmetric sourceType, File file, String key){
		return SecureRegistry.getInstance().encrypt(sourceType,file,key);
	}
	public static byte[] decrypt(Symmetric sourceType, byte[] data, String key){
		return SecureRegistry.getInstance().decrypt(sourceType,data, key);
	}
	public static File decrypt(Symmetric sourceType, File file, String key){
		return SecureRegistry.getInstance().decrypt(sourceType, file, key);
	}
	/**
	 * @return 简化的UUID，去掉了横线
	 */
	public static String simpleUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}

}
