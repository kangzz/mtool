package com.kangzz.mtool.secure.impl;

import com.kangzz.mtool.secure.AbstractSecure;
import com.kangzz.mtool.util.FileUtil;
import com.kangzz.mtool.util.IoUtil;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

/**
 * 描述：DES加密
 * 作者 ：kangzz
 * 日期 ：2016-12-15 23:24:44
 */
public class DesSecure extends AbstractSecure{
    private String keyGeneratorInstance;
    private String cipherInstance;
    private String ivParameterSpec;

    public DesSecure(String keyGeneratorInstance, String cipherInstance, String ivParameterSpec) {
        this.keyGeneratorInstance = keyGeneratorInstance;
        this.cipherInstance = cipherInstance;
        this.ivParameterSpec = ivParameterSpec;
    }

    @Override
    public byte[] encrypt(byte[] data, String key) throws IllegalArgumentException {
        try {
            Cipher cipher = initDESCipher(key,Cipher.ENCRYPT_MODE);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new SecurityException(e);
        }
    }

    @Override
    public byte[] decrypt(byte[] data, String key) throws IllegalArgumentException {
        try {
            Cipher cipher = initDESCipher(key,Cipher.DECRYPT_MODE);
            // 真正开始解密操作
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new SecurityException(e);
        }
    }
    private Cipher initDESCipher(String sKey, int cipherMode) {
        Cipher cipher;
        try {
            DESKeySpec dks = new DESKeySpec(sKey.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(keyGeneratorInstance);
            //key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            cipher = Cipher.getInstance(cipherInstance);
            IvParameterSpec iv = new IvParameterSpec(ivParameterSpec.getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(cipherMode, secretKey, paramSpec);
        } catch (Exception e) {
            throw new SecurityException(e);
        }
        return cipher;
    }
}
