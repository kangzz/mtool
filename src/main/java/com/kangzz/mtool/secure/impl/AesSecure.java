package com.kangzz.mtool.secure.impl;

import com.kangzz.mtool.secure.AbstractSecure;
import com.kangzz.mtool.util.FileUtil;
import com.kangzz.mtool.util.IoUtil;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.SecureRandom;

/**
 * 描述：AES加密
 * 作者 ：kangzz
 * 日期 ：2016-12-15 23:24:58
 */
public class AesSecure  extends AbstractSecure{
    private String keyGeneratorInstance;
    private String secureRandomInstance;;
    private String cipherInstance;

    public AesSecure(String keyGeneratorInstance, String secureRandomInstance, String cipherInstance) {
        this.keyGeneratorInstance = keyGeneratorInstance;
        this.secureRandomInstance = secureRandomInstance;
        this.cipherInstance = cipherInstance;
    }

    @Override
    public byte[] encrypt(byte[] data, String key) throws IllegalArgumentException {
        try {
            Cipher cipher = initAESCipher(key,Cipher.ENCRYPT_MODE);
            byte[] result = cipher.doFinal(data);
            return result; // 加密
        } catch (Exception e) {
            throw new SecurityException(e);
        }
    }

    @Override
    public byte[] decrypt(byte[] data, String key) throws IllegalArgumentException {
        try {
            Cipher cipher = initAESCipher(key,Cipher.DECRYPT_MODE);
            byte[] result = cipher.doFinal(data);
            return result; // 解密
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
            Cipher cipher = initAESCipher(sKey,Cipher.ENCRYPT_MODE);
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
            Cipher cipher = initAESCipher(key,Cipher.DECRYPT_MODE);
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

    private Cipher initAESCipher(String sKey, int cipherMode) {
        //创建Key gen
        KeyGenerator keyGenerator;
        Cipher cipher;
        try {
            keyGenerator = KeyGenerator.getInstance(keyGeneratorInstance);
            SecureRandom secureRandom = SecureRandom.getInstance(secureRandomInstance);
            secureRandom.setSeed(sKey.getBytes());
            // 根据密钥初始化密钥生成器
            keyGenerator.init(128, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] codeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(codeFormat, keyGeneratorInstance);
            cipher = Cipher.getInstance(cipherInstance);
            //初始化
            cipher.init(cipherMode, key , secureRandom);
        } catch (Exception e) {
            throw new SecurityException(e);
        }
        return cipher;
    }
}
