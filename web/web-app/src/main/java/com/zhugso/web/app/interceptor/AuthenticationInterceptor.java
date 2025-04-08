package com.zhugso.web.app.interceptor;


import com.zhugso.common.exception.BoloException;
import com.zhugso.common.result.ResultCodeEnum;
import com.zhugso.common.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取token
        String token = request.getHeader("Authorization");
        if(token == null){
            throw new BoloException(ResultCodeEnum.LOGIN_AUTH);
        }

        // 2. 判断token是否正确，不正确则抛出异常
        JwtUtil.parseToken(token);
        return true;
    }
}
