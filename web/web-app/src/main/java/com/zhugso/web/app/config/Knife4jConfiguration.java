package com.zhugso.web.app.config;

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
                .title("前台网页api")
                .version("1.0")
                .description("用户使用的网页api"));
    }
    @Bean
    public GroupedOpenApi userAPI(){
        return GroupedOpenApi.builder()
                .group("用户api")
                .pathsToMatch("/user/**")
                .build();
    }

    @Bean
    public GroupedOpenApi uploadAPI(){
        return GroupedOpenApi.builder()
                .group("上传api")
                .pathsToMatch("/upload/**")
                .build();
    }

}
