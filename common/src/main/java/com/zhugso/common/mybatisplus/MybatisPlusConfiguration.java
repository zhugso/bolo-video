package com.zhugso.common.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.zhugso.web.*.mapper")
public class MybatisPlusConfiguration {
}
