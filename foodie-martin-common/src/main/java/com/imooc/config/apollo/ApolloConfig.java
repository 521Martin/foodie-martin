//package com.imooc.config.apollo;
//
//import com.ctrip.framework.apollo.core.ConfigConsts;
//import com.ctrip.framework.apollo.model.ConfigChangeEvent;
//import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.context.scope.refresh.RefreshScope;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
///**
// * @author axq
// */
//@Component
//@Slf4j
//public class ApolloConfig {
//    @Resource
//    private RefreshScope refreshScope;
//
//    /**
//     * 监听apollo配置变化
//     *
//     * @param changeEvent 变化事件
//     */
//    @ApolloConfigChangeListener(value = {ConfigConsts.NAMESPACE_APPLICATION})
//    public void onChange(ConfigChangeEvent changeEvent) {
//        for (String changedKey : changeEvent.changedKeys()) {
//            log.info("apollo changed namespace:{} Key:{} value:{}", changeEvent.getNamespace(), changedKey, changeEvent.getChange(changedKey));
//        }
//        refreshScope.refreshAll();
//    }
//
//}
