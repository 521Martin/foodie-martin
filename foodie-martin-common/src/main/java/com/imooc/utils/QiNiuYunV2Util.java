package com.imooc.utils;//package cn.quantgroup.kdsp.common.utils;
//
//import cn.quantgroup.kdsp.common.config.properties.QiniuyunProperties;
//import cn.quantgroup.kdsp.common.exception.BizException;
//import com.google.gson.Gson;
//import com.qiniu.http.Response;
//import com.qiniu.storage.UploadManager;
//import com.qiniu.storage.model.DefaultPutRet;
//import com.qiniu.util.Auth;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.net.URLConnection;
//
///**
// * 七牛云上传工具类
// *
// * @author zyl
// * 这个版本的上传 不用关心文件类型，但是需要先上传，等待传完之后再进行获取文件路径操作
// */
//@Component
//@Slf4j
//public class QiNiuYunV2Util {
//    // 超时时间
//    private static final int SO_TIME_OUT = 60 * 1000;
//    private static final int TIME_OUT = 15 * 1000;
//
//    @Autowired
//    private Auth auth;
//    @Autowired
//    private UploadManager uploadManager;
//    @Autowired
//    private QiniuyunProperties qiniuyunProperties;
//
//    // 覆盖上传
//    private String getUpToken(String fileName) {
//        return auth.uploadToken(qiniuyunProperties.getBucket(), fileName);
//    }
//
//
//    /**
//     * 上传数据流
//     *
//     * @param uploadBytes 数据字节数组
//     * @return
//     */
//
//    public void upload(String fileName, byte[] fileBytes) {
//        try {
//            uploadManager.put(fileBytes, fileName, getUpToken(fileName));
//            log.info("[IFileMgmtService][upload]上传Pdf到七牛完成,filename:{},fileSize:{}", fileName, fileBytes.length);
//        } catch (Exception e) {
//            try {
//                log.error("[IFileMgmtService][upload]上传Pdf到七牛失败,filename:{},fileSize:{},result:{}", fileName, fileBytes.length,  e);
//            } catch (Exception e1) {
//                log.error("[IFileMgmtService][upload]上传Pdf到七牛失败,filename:{},fileSize:{}", fileName, fileBytes.length, e);
//            }
//            throw new RuntimeException("上传Pdf到七牛失败", e);
//        } catch (Exception e) {
//            log.error("[IFileMgmtService][upload]上传Pdf到七牛失败,filename:{},fileSize:{}", fileName, fileBytes.length, e);
//            throw new RuntimeException("上传Pdf到七牛失败", e);
//        }
//    }
//
//    public String getURL(String fileName) {
//        // 有效期 默认 3600 秒
//        return auth.privateDownloadUrl(url + fileName);
//    }
//
//    public byte[] getFileBytes(String fileName) {
//        String fileURL = getURL(fileName);
//        URLConnection conn;
//        try {
//            conn = new URL(fileURL).openConnection();
//            conn.setConnectTimeout(TIME_OUT);
//            conn.setReadTimeout(SO_TIME_OUT);
//        } catch (Exception e) {
//            throw new RuntimeException("七牛文件下载失败", e);
//        }
//        try {
//            InputStream inputStream = conn.getInputStream();
//            return IOUtils.toByteArray(inputStream);
//        } catch (Exception e) {
//            throw new RuntimeException("七牛文件下载失败", e);
//        }
//    }
//}
