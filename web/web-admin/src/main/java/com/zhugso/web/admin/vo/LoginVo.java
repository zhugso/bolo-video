package com.zhugso.web.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "登录信息")
public class LoginVo {

    @Schema(description = "管理员用户名")
    private String name;

    @Schema(description = "管理员密码")
    private String password;

    @Schema(description = "验证码")
    private String captchaCode;

    @Schema(description = "验证码key")
    private String captchaKey;
}
