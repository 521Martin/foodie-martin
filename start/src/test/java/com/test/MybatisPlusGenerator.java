package com.test;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * @Describe: 基于mybatis-plus 插件生产代码的工具
 * @Created by tangfeng 2020-07-20 17:52
 */
public class MybatisPlusGenerator {

    //static String dbUrl = "jdbc:mysql://172.30.220.9:3306/kdsp?useUnicode=true&useSSL=false&characterEncoding=utf8&allowMultiQueries=true";

    static String dbUrl = "jdbc:mysql://172.17.5.11:30077/foodie-shop-dev?useUnicode=true&useSSL=false&characterEncoding=utf8&allowMultiQueries=true";

    static String driverName = "com.mysql.jdbc.Driver";
    static String un = "qa";
    static String pd = "qatest";


    //固定配置 - 自动生产文件的路劲
    static String outPutDir = "a-pre/mybatis-plus";
    //固定配置 - package
    static String parentPackageName = "com.imooc";
    //固定配置 - user -> UserService, 设置成true: user -> IUserService
    static boolean serviceNameStartWithI = true;

    //可变配置
    static String author = "QG Code Generate";
    static String tablePreFix = "t_";


    //可变配置包
    //mybatis 配置
    static String module = "martin";
    static String domainPackage = module + ".entity";
    static String mapperJavaPackage = module + ".mapper";
    static String servicePackage = module + ".service";
    static String serviceImplPackage = module + ".service.impl";
    static String mapperXmlPackage = module + ".resources.mapperxml";


    static List<String> tableNameList = Lists.newArrayList(
            "carousel","category","items","items_comments","items_img","items_param","items_spec","order_items","order_status"
            ,"orders","stu","user_address","users"
    );


    public static void main(String[] args) {
        executor(author, outPutDir, null, (String[]) tableNameList.stream().toArray(String[]::new));
    }

    public static void executor(String author, String outPutDir, Map<String, String> packageMap, String... tableNames) {

        if (packageMap != null && packageMap.size() > 0) {
            domainPackage = packageMap.get("domainPackage");
            mapperJavaPackage = packageMap.get("mapperJavaPackage");
            servicePackage = packageMap.get("servicePackage");
            serviceImplPackage = packageMap.get("serviceImplPackage");
            serviceImplPackage = packageMap.get("serviceImplPackage");
            mapperXmlPackage = packageMap.get("mapperXmlPackage");
        }


        System.out.println("开始生产代码。。。");
        DataSourceConfig dataSourceConfig = getDataSourceConfit();
        StrategyConfig strategyConfig = getStrategyConfig(tableNames);
        GlobalConfig globalConfig = getGlobalConfig(author, outPutDir, serviceNameStartWithI);

        new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(parentPackageName)
                                .setEntity(domainPackage)
                                .setMapper(mapperJavaPackage)
                                .setService(servicePackage)
                                .setServiceImpl(serviceImplPackage)
                                .setXml(mapperXmlPackage)
                                .setController("controllertest")
                )
                .execute();
        System.out.println("生产代码结束。。。");
    }


    /**
     * 数据源配置
     *
     * @return
     */
    private static DataSourceConfig getDataSourceConfit() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername(un)
                .setPassword(pd)
                .setDriverName(driverName);
        return dataSourceConfig;
    }


    /**
     * 配置生成的类和属性的命名配置
     *
     * @param tableNames
     * @return
     */
    private static StrategyConfig getStrategyConfig(String... tableNames) {
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setTablePrefix(tablePreFix)
                .setSuperEntityClass("com.imooc.pojo.supers.BaseEntity")
                .setSuperEntityColumns("id", "enable", "created_at", "updated_at")
                .setCapitalMode(true)
                //是否使用lombokModel
                .setEntityLombokModel(true)
                //false: 实体属性注解bind带下划线的db字段，true:不注解实体属性
                //.setDbColumnUnderline(true)
                //命名策略：nochange：创建类和属性和数据库表保持一致（类名和属性名包含下划线）；underline_to_camel：正常的驼峰命名方式
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        return strategyConfig;
    }

    private static GlobalConfig getGlobalConfig(String author, String outPutDir, boolean serviceNameStartWithI) {
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false)
                .setAuthor(author)
                .setOutputDir(outPutDir)
                //生成代码后是否打开目录
                .setOpen(false)
                .setEnableCache(false)
                .setBaseColumnList(true)
                .setBaseResultMap(true)
                .setFileOverride(true)
                .setServiceName("I%sService")
                .setServiceImplName("%sServiceImpl");

        //user -> UserService, 设置成true: user -> IUserService
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }

        return config;
    }

}
