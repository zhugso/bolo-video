package com.zhugso.web.app.controller;

import com.zhugso.common.result.ResultData;
import com.zhugso.common.utils.JwtUtil;
import com.zhugso.model.entity.User;
import com.zhugso.web.app.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户信息管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;


    @GetMapping("info")
    public ResultData<?> getUserInfo(@RequestHeader String token){

        User user = userService.getById(JwtUtil.getUserIdToken(token));





        return ResultData.success(null);
    }



}
