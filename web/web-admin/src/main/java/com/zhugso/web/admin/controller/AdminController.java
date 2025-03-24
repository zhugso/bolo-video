package com.zhugso.web.admin.controller;

import com.zhugso.common.result.ResultData;
import com.zhugso.common.utils.JwtUtil;
import com.zhugso.model.entity.Admin;
import com.zhugso.web.admin.service.AdminService;
import com.zhugso.web.admin.vo.AdminVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "个人信息管理")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @GetMapping("getInfo")
    public ResultData<AdminVo> getAdminInfo(@RequestHeader("Authorization") String token){
        Long userId = JwtUtil.getUserIdToken(token);
        Admin adminInfo = adminService.getById(userId);

        return ResultData.success(
                new AdminVo(adminInfo.getAdminName(),adminInfo.getAdminName()));
    }

}
