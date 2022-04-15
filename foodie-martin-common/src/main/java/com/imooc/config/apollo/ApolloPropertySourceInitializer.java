
package com.imooc.config.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.build.ApolloInjector;
import com.ctrip.framework.apollo.core.enums.Env;
import com.ctrip.framework.apollo.internals.ConfigServiceLocator;
import com.ctrip.framework.apollo.util.http.HttpRequest;
import com.ctrip.framework.apollo.util.http.HttpResponse;
import com.ctrip.framework.apollo.util.http.HttpUtil;
import com.ctrip.framework.foundation.Foundation;
import com.ctrip.framework.foundation.internals.provider.DefaultApplicationProvider;
import com.ctrip.framework.foundation.internals.provider.DefaultServerProvider;
import com.google.common.collect.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@Configuration
public class ApolloPropertySourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {
    private int order;
    private final Multimap<Integer, String> NAMESPACE_NAMES;
    private static final Logger logger = LoggerFactory.getLogger(ConfigServiceLocator.class);
    private static final int DEFAULT_ORDER = 2147483647;
    private static final String DEFAULT_NAMESPACE = "application";

    public ApolloPropertySourceInitializer() {
        this(Integer.valueOf(Foundation.app().getProperty("order", String.valueOf(2147483647))), StringUtils.commaDelimitedListToStringArray(Foundation.app().getProperty("namespace", "application")));
    }

    public ApolloPropertySourceInitializer(String... namespace) {
        this(2147483647, namespace);
    }

    public ApolloPropertySourceInitializer(int order, String... namespace) {
        this.order = -2147483643;
        this.NAMESPACE_NAMES = HashMultimap.create();
        ArrayList<String> namespaceArr = Lists.newArrayList(namespace);
        if (!namespaceArr.contains("application")) {
            this.NAMESPACE_NAMES.putAll(2147483647, Lists.newArrayList(new String[]{"application"}));
        }

        this.NAMESPACE_NAMES.putAll(order, namespaceArr);
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        if (!environment.getPropertySources().contains("ApolloPropertySources")) {
            this.initIpAndNamespace(environment);
            this.initKubernetesEnv(environment);
//            logger.info("Fetching remote config by apollo and try to reinitialize logging system if necessary.");
            CompositePropertySource composite = new CompositePropertySource("ApolloPropertySources");
            ImmutableSortedSet<Integer> orders = ImmutableSortedSet.copyOf(this.NAMESPACE_NAMES.keySet());
            UnmodifiableIterator iterator = orders.iterator();

            while (iterator.hasNext()) {
                int order = (Integer) iterator.next();
                Iterator it = this.NAMESPACE_NAMES.get(order).iterator();

                while (it.hasNext()) {
                    String namespace = (String) it.next();
                    Config config = ConfigService.getConfig(namespace);
                    ConfigPropertySource configPropertySource = new ConfigPropertySource(namespace, config);
                    composite.addPropertySource(configPropertySource);
                }
            }

            environment.getPropertySources().addFirst(composite);
            DefaultApplicationProvider defaultApplicationProvider = new DefaultApplicationProvider();
            defaultApplicationProvider.initialize();
            DefaultServerProvider defaultServerProvider = new DefaultServerProvider();
            defaultServerProvider.initialize();
        }
    }

    private void initIpAndNamespace(ConfigurableEnvironment environment) {
        String localhost = environment.getProperty("tech.localhost", Foundation.app().getProperty("tech.localhost", (String) null));
        if (StringUtils.isEmpty(localhost)) {
            localhost = Foundation.net().getHostAddress();
        } else {
            logger.warn("获取 tech.localhost 信息结果 : {}, 测试环境应为本机IP", localhost);
        }

        environment.getSystemProperties().put("tech.localhost", localhost);
        String[] ipSplit = localhost.split("\\.");
        String localNamespace = environment.getProperty("NAMESPACE", ipSplit[ipSplit.length - 1]);
        environment.getSystemProperties().putIfAbsent("NAMESPACE", localNamespace);
    }

    private void initKubernetesEnv(ConfigurableEnvironment environment) {
        if (System.getenv("NAMESPACE") != null) {
            logger.info("运行在 kubernets 环境.");
        } else {
            boolean isPro = Env.PRO.name().equalsIgnoreCase(Foundation.server().getEnvType());
            if (isPro) {
                logger.info("哇, 生产环境. 配置中心静悄悄. 什么都不敢做.");
            } else {
                String namespace = environment.getProperty("NAMESPACE", Foundation.app().getProperty("NAMESPACE", (String) null));
                if (namespace == null) {
                    logger.info("你好像没有配置 NAMESPACE 哦?你不打算连接到 kubernetes 内部么?");
                } else {
                    HttpUtil httpUtil = (HttpUtil) ApolloInjector.getInstance(HttpUtil.class);
                    String kubernetesServer = Foundation.app().getProperty("eos_server_host", "http://eos.quantgroups.com/");
                    HttpRequest httpRequest = new HttpRequest(kubernetesServer + "api/apollo/env_vars?namespace=" + namespace);
                    HttpResponse<KubeEnvInfo> mapHttpResponse = httpUtil.doGet(httpRequest, KubeEnvInfo.class);
                    KubeEnvInfo body = (KubeEnvInfo) mapHttpResponse.getBody();
                    if (body != null && body.success) {
                        logger.info("客官请放心, kubernets 的环境变量已经注入, 您可以放心的在 kubernetes 之外启动你的服务了");
                        environment.getSystemProperties().putAll(body.details);
                    } else {
                        logger.error("额... 看起来 kubernetes server 有点问题, 返回了false, serverInfo:{} ,body:{}", kubernetesServer, body);
                    }
                }
            }
        }
    }


    private static class KubeEnvInfo {
        boolean success;
        Map<String, String> details;

        private KubeEnvInfo() {
        }
    }
}
