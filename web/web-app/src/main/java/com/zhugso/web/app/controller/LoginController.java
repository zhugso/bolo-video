package com.zhugso.web.app.controller;

import com.zhugso.common.result.ResultData;
import com.zhugso.web.app.service.LoginService;
import com.zhugso.web.app.vo.CaptchaVo;
import com.zhugso.web.app.vo.LoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户登录管理")
@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    LoginService loginService;

    @Operation(summary = "获取图形验证码")
    @GetMapping("log*/captcha")
    public ResultData<CaptchaVo> getCaptcha(){
        CaptchaVo res = loginService.getCaptcha();
        return ResultData.success(res);
    }

    @Operation(summary = "用户登录")
    @PostMapping("login")
    public ResultData<String> login(@RequestBody LoginVo loginVo){
        String jwt = loginService.login(loginVo);
        return ResultData.success(jwt);
    }

    @Operation(summary = "用户注册")
    @PostMapping("logon")
    public ResultData<String> logon(@RequestBody LoginVo loginVo){
        loginService.logon(loginVo);
        return ResultData.success(null);
    }

}
