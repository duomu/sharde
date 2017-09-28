package com.yll.sharde.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author：linlin.yang
 * @date：2017/8/28 16:30
 */
@Configuration
@ComponentScan(basePackages = {Config.PACKAGE_SERVICE})
//@Import({MybatisConfig.class})/*不分表注解配置*/
//@Import({MybatisXmlConfig.class})/*不分表xml配置*/
//@Import({MybatisShardingConfig.class})/*分表注解配置*/
//@Import({MybatisShardingXmlConfig.class})/*分表xml配置*/
public class Config {
    //注意不要扫描com.yll.sharde.web，不然启动应用时会重复注册DispatcherServlet
//    static final String PACKAGE_CONFIG = "com.yll.sharde.config";
    static final String PACKAGE_SERVICE = "com.yll.sharde.service";
}
