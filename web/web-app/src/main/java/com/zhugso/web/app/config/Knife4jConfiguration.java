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
    public GroupedOpenApi personAPI(){
        return GroupedOpenApi.builder()
                .group("个人中心管理")
                .pathsToMatch("/person/**")
                .build();
    }

    @Bean
    public GroupedOpenApi searchAPI(){
        return GroupedOpenApi.builder()
                .group("信息搜索")
                .pathsToMatch("/search/**")
                .build();
    }

    @Bean
    public GroupedOpenApi videoAPI(){
        return GroupedOpenApi.builder()
                .group("视频信息")
                .pathsToMatch("/video/**")
                .build();
    }
    @Bean
    public GroupedOpenApi loginAPI(){
        return GroupedOpenApi.builder()
                .group("登录api")
                .pathsToMatch("/login/**")
                .build();
    }

    @Bean
    public GroupedOpenApi logonAPI(){
        return GroupedOpenApi.builder()
                .group("注册api")
                .pathsToMatch("/logon/**")
                .build();
    }

}
