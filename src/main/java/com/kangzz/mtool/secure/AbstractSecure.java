package com.kangzz.mtool.secure;



import java.io.File;

/**
 * 描述：抽象加密解密
 * 作者 ：kangzz
 * 日期 ：2016-12-14 18:53:19
 */
public abstract class AbstractSecure implements Secure{
	@Override
	public byte[] encrypt(byte[] data) throws IllegalArgumentException {
		return null;
	}
	@Override
	public File encrypt(File file) throws IllegalArgumentException {
		return null;
	}
	@Override
	public byte[] encrypt(byte[] data, String key) throws IllegalArgumentException {
		return null;
	}
	@Override
	public byte[] decrypt(byte[] data, String key) throws IllegalArgumentException {
		return null;
	}
	@Override
	public File encrypt(File file, String key) throws IllegalArgumentException {
		return null;
	}
	@Override
	public File decrypt(File file, String key) throws IllegalArgumentException {
		return null;
	}
}
