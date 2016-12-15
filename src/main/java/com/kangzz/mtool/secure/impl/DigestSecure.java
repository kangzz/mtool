package com.kangzz.mtool.secure.impl;

import com.kangzz.mtool.enums.FileType;
import com.kangzz.mtool.exception.UtilException;
import com.kangzz.mtool.secure.AbstractSecure;
import com.kangzz.mtool.secure.DigestUtil;
import com.kangzz.mtool.secure.SecureException;
import com.kangzz.mtool.util.FileUtil;
import com.kangzz.mtool.util.IoUtil;
import com.kangzz.mtool.util.StrUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kangzz on 16/12/15.
 */
public class DigestSecure extends AbstractSecure {
    private String secureType;
    public DigestSecure(String secureType){
        this.secureType = secureType;
    }
    @Override
    public byte[] encrypt(byte[] data) throws IllegalArgumentException {
        return DigestUtil.getDigest(secureType).digest(data);
    }
    @Override
    public File encrypt(File file) throws IllegalArgumentException {
        InputStream in = null;
        File encryptFile = null;
        FileOutputStream outputStream = null;
        try {
            in = FileUtil.getInputStream(file);
            FileType fileType = FileUtil.getType(in);
            encryptFile = File.createTempFile(FileUtil.mainName(file),"."+FileUtil.extName(file));
            outputStream = new FileOutputStream(encryptFile);
            outputStream.write(DigestUtil.digest(DigestUtil.getDigest(secureType), in));
            outputStream.flush();
        } catch (Exception e) {
            throw new UtilException(e);
        }finally{
            IoUtil.close(in);
            IoUtil.close(outputStream);
        }
        return encryptFile;
    }
}
