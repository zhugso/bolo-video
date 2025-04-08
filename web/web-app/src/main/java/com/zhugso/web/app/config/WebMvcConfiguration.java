package com.zhugso.web.app.config;

import com.zhugso.web.app.interceptor.AuthenticationInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Resource
    AuthenticationInterceptor authenticationInterceptor;

    /*
        注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login/**", "/user/videoCards/**");

    }
}
