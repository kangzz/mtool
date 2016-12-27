package com.kangzz.mtool.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.kangzz.mtool.exception.UtilException;
import com.kangzz.mtool.log.Log;
import com.kangzz.mtool.log.StaticLog;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * Created by kangzz on 16/12/27.
 */
public class FTPClientUtil {

    private final static Log log = StaticLog.get();

    private FTPClientUtil(){

    }
    /**
     * 连接文件服务器
     * @param address 文件服务器地址
     * @param port 端口
     * @param username 用户名
     * @param password 密码
     * @throws Exception
     */
     public static FTPClient  connect(String address, int port, String username, String password) {
        
        FTPClient ftpClient = new FTPClient();
        try {
            // 连接
            ftpClient.connect(address, port);
            // 登录
            ftpClient.login(username, password);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        } catch (Exception e) {
            log.error("【连接文件服务器失败】", e);
            throw new UtilException("连接文件服务器失败");
        }
        // 判断文件服务器是否可用？？
        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            closeConnection(ftpClient);
        }
        return ftpClient;
    }

    /**
     * 连接文件服务器
     * @param address 文件服务器地址
     * @param port 端口
     * @param username 用户名
     * @param password 密码
     * @param workingDirectory 目标连接工作目录
     * @throws Exception
     */
    public static FTPClient connect(String address, int port, String username, String password, String workingDirectory)
            throws Exception {
        FTPClient ftpClient = connect(address, port, username, password);
        changeWorkingDirectory(workingDirectory, ftpClient);
        return ftpClient;
    }

    /**
     * 关闭连接，使用完连接之后，一定要关闭连接，否则服务器会抛出 Connection reset by peer的错误
     * @throws IOException
     */
    public static void closeConnection(FTPClient ftpClient) {
        log.info("【关闭文件服务器连接】");
        if (ftpClient == null) {
            return;
        }

        try {
            ftpClient.disconnect();
        } catch (IOException e) {
            log.error("【关闭连接失败】", e);
            throw new UtilException("关闭连接失败");
        }
    }

    /**
     * 切换工作目录
     * @param directory 目标工作目录
     * @param ftpClient
     * @throws IOException
     */
    public static void changeWorkingDirectory(String directory, FTPClient ftpClient) {
        log.info("【切换工作目录】directory : " + directory);
        // 切换到目标工作目录
        try {
            if (!ftpClient.changeWorkingDirectory(directory)) {
                ftpClient.makeDirectory(directory);
                ftpClient.changeWorkingDirectory(directory);
            }
        } catch (IOException e) {
            log.error("【切换工作目录失败】", e);
            throw new UtilException("切换工作目录失败");
        }
    }

    /**
     * 上传文件/文件夹
     * @param file 上传的文件或文件夹
     * @return 文件存放的路径以及文件名
     * @throws Exception
     */
    public static void upload(File file, FTPClient ftpClient) throws Exception {
        if (file == null) {
            log.warn("【存储的文件为空】");
            throw new UtilException("上传文件为空");
        }
        log.info("【上传文件/文件夹】file ： " + file.getName());

        // 是文件，直接上传
        if (!file.isDirectory()) {
            storeFile(new File(file.getPath()), ftpClient);
            return;
        }

        changeWorkingDirectory(file.getName(), ftpClient);
        // 文件夹，递归上传所有文件
        for (File item : file.listFiles()) {
            if (!item.isDirectory()) {
                storeFile(item, ftpClient);
                continue;
            }
            upload(item, ftpClient);
            ftpClient.changeToParentDirectory();
        }
    }

    /**
     * 删除文件
     * @param fileName 要删除的文件地址
     * @return true/false
     * @throws IOException
     */
    public static boolean delete(String fileName, FTPClient ftpClient) throws IOException {
        log.info("【删除文件】fileName ： " + fileName);
        return ftpClient.deleteFile(fileName);
    }

    /**
     * 存储文件
     * @param file {@link File}
     * @throws Exception
     */
    public static void storeFile(File file, FTPClient ftpClient) throws Exception {
        if (file == null) {
            log.warn("【存储的文件为空】");
            throw new UtilException("存储的文件为空");
        }
        log.info("【存储文件】file ： " + file.getName());

        FileInputStream input = new FileInputStream(file);
        ftpClient.storeFile(file.getName(), input);
        input.close();
    }

    /**
     * 下载文件到指定目录
     * @param ftpFile 文件服务器上的文件地址
     * @param dstFile 输出文件的路径和名称
     * @throws Exception
     */
    public static void downLoad(String ftpFile, String dstFile, FTPClient ftpClient) throws Exception {
        log.info("【下载文件到指定目录】ftpFile = " + ftpFile + " , dstFile = " + dstFile);
        if (StrUtil.isBlank(ftpFile)) {
            log.warn("【参数ftpFile为空】");
            throw new UtilException("【参数ftpFile为空】");
        }
        if (StrUtil.isBlank(dstFile)) {
            log.warn("【参数dstFile为空】");
            throw new UtilException("【参数dstFile为空】");
        }
        File file = new File(dstFile);
        FileOutputStream fos = new FileOutputStream(file);
        ftpClient.retrieveFile(ftpFile, fos);
        fos.flush();
        fos.close();
    }

    /**
     * 从文件服务器获取文件流
     * @param ftpFile 文件服务器上的文件地址
     * @return {@link InputStream}
     * @throws IOException
     */
    public static InputStream retrieveFileStream(String ftpFile, FTPClient ftpClient) throws IOException {
        log.info("【从文件服务器获取文件流】ftpFile ： " + ftpFile);
        if (StrUtil.isBlank(ftpFile)) {
            log.warn("【参数ftpFile为空】");
            throw new UtilException("【参数ftpFile为空】");
        }
        return ftpClient.retrieveFileStream(ftpFile);
    }


}
