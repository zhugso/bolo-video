package com.zhugso.web.admin.controller;

import com.zhugso.common.result.ResultData;
import com.zhugso.web.admin.service.LoginService;
import com.zhugso.web.admin.vo.CaptchaVo;
import com.zhugso.web.admin.vo.LoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@Tag(name = "登录管理")
@RestController
@RequestMapping("/admin")
public class LoginController {

    @Resource
    LoginService loginService;

    @Operation(summary = "获取图形验证码")
    @GetMapping("login/captcha")
    public ResultData<CaptchaVo> getCaptcha(){
        CaptchaVo res = loginService.getCaptcha();
        return ResultData.success(res);
    }

    @Operation(summary = "登录")
    @PostMapping("login")
    public ResultData<String> login(@RequestBody LoginVo loginVo){
        String jwt = loginService.login(loginVo);
        return ResultData.success(jwt);
    }



}
