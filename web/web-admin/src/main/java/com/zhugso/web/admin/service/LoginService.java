package com.zhugso.web.admin.service;

import com.zhugso.web.admin.vo.CaptchaVo;
import com.zhugso.web.admin.vo.LoginVo;

public interface LoginService {

    CaptchaVo getCaptcha();

    String login(LoginVo loginVo);
}
