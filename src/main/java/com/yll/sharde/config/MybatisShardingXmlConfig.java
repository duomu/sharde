package com.yll.sharde.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author：linlin.yang
 * @date：2017/8/28 16:44
 */
@Configuration
@ImportResource("classpath:spring/mybatisShardingContext.xml")
public class MybatisShardingXmlConfig {
}
