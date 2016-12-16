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
    @Override
    public File encrypt(File sourceFile, String sKey) throws IllegalArgumentException {
        //新建临时加密文件
        File encryptFile = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(sourceFile);
            encryptFile = File.createTempFile(FileUtil.mainName(sourceFile),"."+FileUtil.extName(sourceFile));
            outputStream = new FileOutputStream(encryptFile);
            Cipher cipher = initDESCipher(sKey,Cipher.ENCRYPT_MODE);
            //以加密流写入文件
            CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
            byte[] cache = new byte[1024];
            int nRead = 0;
            while ((nRead = cipherInputStream.read(cache)) != -1) {
                outputStream.write(cache, 0, nRead);
                outputStream.flush();
            }
            cipherInputStream.close();
        } catch (Exception e) {
            throw new SecurityException(e);
        } finally {
            IoUtil.close(inputStream);
            IoUtil.close(outputStream);
        }
        return encryptFile;
    }

    @Override
    public File decrypt(File sourceFile, String key) throws IllegalArgumentException {
        File decryptFile = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            Cipher cipher = initDESCipher(key,Cipher.DECRYPT_MODE);
            inputStream = new FileInputStream(sourceFile);
            decryptFile = File.createTempFile(FileUtil.mainName(sourceFile),"."+FileUtil.extName(sourceFile));
            outputStream = new FileOutputStream(decryptFile);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);
            byte [] buffer = new byte [1024];
            int r;
            while ((r = inputStream.read(buffer)) >= 0) {
                cipherOutputStream.write(buffer, 0, r);
            }
            cipherOutputStream.close();
        } catch (Exception e) {
            throw new SecurityException(e);
        } finally {
            IoUtil.close(inputStream);
            IoUtil.close(outputStream);
        }
        return decryptFile;
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
