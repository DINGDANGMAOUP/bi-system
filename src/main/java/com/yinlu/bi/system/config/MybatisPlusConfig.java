package com.yinlu.bi.system.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yinlu.bi.system.mapper")
public class MybatisPlusConfig {

}
