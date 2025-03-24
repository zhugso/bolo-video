package com.zhugso.web.admin.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfiguration {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
                .title("管理员后台系统api")
                .version("1.0")
                .description("管理员后台系统api"));
    }

    @Bean
    public GroupedOpenApi loginAPI(){
        return GroupedOpenApi.builder()
                .group("admin管理")
                .pathsToMatch("/admin/**")
                .build();
    }

}
