package com.imooc.properties;

import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * 七牛云相关配置
 *
 * @author axq
 */
@Configuration
@Getter
@Setter
public class QiniuyunProperties implements Serializable {

    /**
     * 访问密钥
     */
    @Value("${qiniuyun.access_key}")
    private String accessKey;
    /**
     * 访问密钥
     */
    @Value("${qiniuyun.secret_key}")
    private String secretKey;

    /**
     * bucket(空间名称)
     */
    @Value("${qiniuyun.bucket}")
    private String bucket;
    /**
     * 文件链接前缀
     */
    @Value("${qiniuyun.url.prefix}")
    private String linkPrefix;

    @Bean
    public Auth auth() {
        return Auth.create(accessKey, secretKey);
    }

    @Bean
    public UploadManager uploadManager() {
        //构造一个带指定 Region 对象的配置类
        com.qiniu.storage.Configuration cfg = new com.qiniu.storage.Configuration(Region.huanan());
        //...其他参数参考类注释
        return new UploadManager(cfg);
    }
}
