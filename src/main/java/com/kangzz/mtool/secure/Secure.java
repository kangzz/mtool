package com.kangzz.mtool.secure;

import java.io.File;

/**
 * 描述：安全接口
 * 作者 ：kangzz
 * 日期 ：2016-12-14 15:28:49
 */
public interface Secure<T>{
	/**
	 * 描述：加密
	 * 作者 ：kangzz
	 * 日期 ：2016-12-14 18:10:47
	 */
	public byte[] encrypt(byte[] data) throws IllegalArgumentException;
	/**
	 * 描述：加密文件
	 * 作者 ：kangzz
	 * 日期 ：2016-12-15 13:30:30
	 */
	public File encrypt(File file) throws IllegalArgumentException;
	/**
	 * 描述：加密
	 * 作者 ：kangzz
	 * 日期 ：2016-12-14 18:10:47
	 */
	public byte[] encrypt(byte[] data, String key) throws IllegalArgumentException;
	/**
	 * 描述：解密
	 * 作者 ：kangzz
	 * 日期 ：2016-12-14 18:12:06
	 */
	public byte[] decrypt(byte[] data, String key) throws IllegalArgumentException;

	/**
	 * 描述：加密文件
	 * 作者 ：kangzz
	 * 日期 ：2016-12-15 13:30:30
	 */
	public File encrypt(File file, String key) throws IllegalArgumentException;
	/**
	 * 描述：解密文件
	 * 作者 ：kangzz
	 * 日期 ：2016-12-15 13:30:37
	 */
	public File decrypt(File file, String key) throws IllegalArgumentException;
}