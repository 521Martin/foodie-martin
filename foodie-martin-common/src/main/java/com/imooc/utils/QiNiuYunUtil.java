package com.imooc.utils;


import com.google.gson.Gson;
import com.imooc.exception.BizException;
import com.imooc.properties.QiniuyunProperties;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * 七牛云上传工具类
 *
 * @author axq
 */
@Component
@Slf4j
public class QiNiuYunUtil {
    @Autowired
    private Auth auth;
    @Autowired
    private UploadManager uploadManager;
    @Autowired
    private QiniuyunProperties qiniuyunProperties;

    /**
     * 上传数据流
     *
     * @param uploadBytes 数据字节数组
     * @return
     */
    public String upload(byte[] uploadBytes, String contentType) {
        //解析上传成功的结果
        ByteArrayInputStream byteInputStream = null;
        try {
            byteInputStream = new ByteArrayInputStream(uploadBytes);
            String upToken = auth.uploadToken(qiniuyunProperties.getBucket());
            Response response = uploadManager.put(byteInputStream, null, upToken, null, contentType);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return qiniuyunProperties.getLinkPrefix() + putRet.hash;
        } catch (Exception e) {
            throw new BizException("上传数据流异常");
        } finally {
            if (byteInputStream != null) {
                try {
                    byteInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public String overrideUpload(byte[] uploadBytes, String contentType, String fileName) {
        //解析上传成功的结果
        ByteArrayInputStream byteInputStream = null;
        try {
            byteInputStream = new ByteArrayInputStream(uploadBytes);
            String upToken = auth.uploadToken(qiniuyunProperties.getBucket(), fileName);
            Response response = uploadManager.put(byteInputStream, fileName, upToken, null, contentType);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return qiniuyunProperties.getLinkPrefix() + putRet.hash;
        } catch (Exception e) {
            throw new BizException("上传数据流异常");
        } finally {
            if (byteInputStream != null) {
                try {
                    byteInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String overrideUploadFileName(byte[] uploadBytes, String contentType, String fileName) {
        //解析上传成功的结果
        ByteArrayInputStream byteInputStream = null;
        try {
            byteInputStream = new ByteArrayInputStream(uploadBytes);
            String upToken = auth.uploadToken(qiniuyunProperties.getBucket(), fileName);
            Response response = uploadManager.put(byteInputStream, fileName, upToken, null, contentType);

            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return qiniuyunProperties.getLinkPrefix() + putRet.key;
        } catch (Exception e) {
            throw new BizException("上传数据流异常");
        } finally {
            if (byteInputStream != null) {
                try {
                    byteInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 上传本地文件
     *
     * @param localFilePath 本地文件路径
     * @param fileName      上传保存文件名
     * @return 文件外链
     */
    public String upload(String localFilePath, String fileName) {
        try {
            String upToken = auth.uploadToken(qiniuyunProperties.getBucket());
            Response response = uploadManager.put(localFilePath, fileName, upToken);
            if (response.isOK()) {
                return qiniuyunProperties.getLinkPrefix() + fileName;
            }
        } catch (QiniuException e) {
            log.info("七牛云上传本地文件:{} | 发生异常", localFilePath, e);
        }
        return null;
    }
}
