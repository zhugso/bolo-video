package com.zhugso.web.app.controller;

import com.zhugso.common.result.ResultData;
import com.zhugso.common.utils.JwtUtil;
import com.zhugso.model.entity.User;
import com.zhugso.web.app.service.UserService;
import com.zhugso.web.app.vo.UserHeadInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户信息管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Operation(summary = "获取顶部用户信息")
    @GetMapping("head-user")
    public ResultData<UserHeadInfoVo> getUserInfo(@RequestHeader("Authorization") String token){

        User user = userService.getById(JwtUtil.getUserIdToken(token));

        UserHeadInfoVo userHeadInfoVo = new UserHeadInfoVo();

        BeanUtils.copyProperties(user, userHeadInfoVo);

        // 暂未实现
        userHeadInfoVo.setFollowNums(999);
        userHeadInfoVo.setFansNums(999);
        userHeadInfoVo.setDynamicNums(999);

        return ResultData.success(userHeadInfoVo);
    }



    @Operation(summary = "修改头像")
    @PostMapping("avatar/{avatarKey}")
    public ResultData<?> avatarModify(@RequestHeader("Authorization") String token,
                                      @PathVariable("avatarKey") String avatarKey){
        Long userId = JwtUtil.getUserIdToken(token);
        User user = userService.getById(userId);

        String url = stringRedisTemplate.opsForValue().get(avatarKey);

        user.setAvatarUrl(url);

        userService.saveOrUpdate(user);

        return ResultData.success();
    }

    @Operation(summary = "获取头像")
    @GetMapping("avatar")
    public ResultData<String> getAvatar(@RequestHeader("Authorization") String token){
        Long userId = JwtUtil.getUserIdToken(token);
        User user = userService.getById(userId);
        return ResultData.success(user.getAvatarUrl());
    }

}
