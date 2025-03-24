package com.zhugso.web.app.service;


import com.zhugso.web.app.vo.CaptchaVo;
import com.zhugso.web.app.vo.LoginVo;

public interface LoginService {


    CaptchaVo getCaptcha();

    String login(LoginVo loginVo);

    void logon(LoginVo loginVo);
}
