package com.zhugso.web.app.controller;

import com.zhugso.common.result.ResultData;
import com.zhugso.common.utils.JwtUtil;
import com.zhugso.model.entity.User;
import com.zhugso.web.app.service.UserService;
import com.zhugso.web.app.vo.UserHeadInfoVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
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


}
