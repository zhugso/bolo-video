package com.zhugso.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wf.captcha.SpecCaptcha;
import com.zhugso.common.constant.RedisConstant;
import com.zhugso.common.exception.BoloException;
import com.zhugso.common.result.ResultCodeEnum;
import com.zhugso.common.utils.JwtUtil;
import com.zhugso.model.entity.Admin;
import com.zhugso.web.admin.mapper.AdminMapper;
import com.zhugso.web.admin.service.LoginService;
import com.zhugso.web.admin.vo.CaptchaVo;
import com.zhugso.web.admin.vo.LoginVo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private AdminMapper adminMapper;

    @Override
    public CaptchaVo getCaptcha() {
        // 获取图片验证码
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        // 获取图片验证码文本
        String code = specCaptcha.text().toLowerCase();
        // 设置redis key
        String key = RedisConstant.ADMIN_LOGIN_PREFIX + UUID.randomUUID();
        // 存入redis 超时时间60s，k：key，v：code
        stringRedisTemplate.opsForValue()
                .set(key, code, RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);
        // 将图片验证码经Base64转为字符串
        return new CaptchaVo(specCaptcha.toBase64(), key);
    }

    @Override
    public String login(LoginVo loginVo) {
        System.out.println(loginVo.toString());
        // 1.校验验证码
        if (loginVo.getCaptchaCode() == null){
            throw new BoloException(ResultCodeEnum.CAPTCHA_CODE_NOT_FOUNT);
        }

        String code = stringRedisTemplate.opsForValue().get(loginVo.getCaptchaKey());
        if (code == null){
            throw new BoloException(ResultCodeEnum.CAPTCHA_CODE_ERROR);
        }

        if(!code.equalsIgnoreCase(loginVo.getCaptchaCode())){
            throw new BoloException(ResultCodeEnum.CAPTCHA_CODE_ERROR);
        }

        // 2.管理员用户验证
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getAdminName, loginVo.getName());
        Admin admin = adminMapper.selectOne(queryWrapper);

        if (admin == null){
            throw new BoloException(ResultCodeEnum.ACCOUNT_NOT_EXIST_ERROR);
        }
        if(!admin.getPassword().equals(DigestUtils.md5Hex(loginVo.getPassword()))){
            throw new BoloException(ResultCodeEnum.ACCOUNT_ERROR);
        }

        // 3.创建jwt
        return JwtUtil.createToken(admin.getAdminId(), admin.getAdminName());
    }
}
