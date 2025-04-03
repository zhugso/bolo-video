package com.zhugso.web.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wf.captcha.SpecCaptcha;
import com.zhugso.common.constant.RedisConstant;
import com.zhugso.common.exception.BoloException;
import com.zhugso.common.result.ResultCodeEnum;
import com.zhugso.common.utils.JwtUtil;
import com.zhugso.model.entity.User;
import com.zhugso.web.app.mapper.UserMapper;
import com.zhugso.web.app.service.LoginService;
import com.zhugso.web.app.vo.CaptchaVo;
import com.zhugso.web.app.vo.LoginVo;
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
    private UserMapper userMapper;

    @Override
    public CaptchaVo getCaptcha() {
        // 获取图片验证码
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        // 获取图片验证码文本
        String code = specCaptcha.text().toLowerCase();
        // 设置redis key
        String key = RedisConstant.USER_LOGIN_PREFIX + UUID.randomUUID();
        // 存入redis 超时时间60s，k：key，v：code
        stringRedisTemplate.opsForValue()
                .set(key, code, RedisConstant.USER_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);
        // 将图片验证码经Base64转为字符串
        return new CaptchaVo(specCaptcha.toBase64(), key);
    }

    @Override
    public String login(LoginVo loginVo) {
        // 1.校验验证码
        if (loginVo.getCaptchaCode() == null) {
            throw new BoloException(ResultCodeEnum.CAPTCHA_CODE_NOT_FOUNT);
        }

        String code = stringRedisTemplate.opsForValue().get(loginVo.getCaptchaKey());
        if (code == null) {
            throw new BoloException(ResultCodeEnum.CAPTCHA_CODE_ERROR);
        }

        if (!code.equalsIgnoreCase(loginVo.getCaptchaCode())) {
            throw new BoloException(ResultCodeEnum.CAPTCHA_CODE_ERROR);
        }

        // 2.管理员用户验证
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, loginVo.getName());
        User user = userMapper.selectOne(queryWrapper);

        if (user == null) {
            throw new BoloException(ResultCodeEnum.ACCOUNT_NOT_EXIST_ERROR);
        }
        if (!user.getPassword().equals(DigestUtils.md5Hex(loginVo.getPassword()))) {
            throw new BoloException(ResultCodeEnum.ACCOUNT_ERROR);
        }

        // 3.创建jwt
        return JwtUtil.createToken(user.getUserId(), user.getUsername());
    }

    @Override
    public void logon(LoginVo loginVo) {
        // 1.校验验证码
        if (loginVo.getCaptchaCode() == null) {
            throw new BoloException(ResultCodeEnum.CAPTCHA_CODE_NOT_FOUNT);
        }

        String code = stringRedisTemplate.opsForValue().get(loginVo.getCaptchaKey());
        if (code == null) {
            throw new BoloException(ResultCodeEnum.CAPTCHA_CODE_ERROR);
        }

        if (!code.equalsIgnoreCase(loginVo.getCaptchaCode())) {
            throw new BoloException(ResultCodeEnum.CAPTCHA_CODE_ERROR);
        }

        // 2. 判断用户是否存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, loginVo.getName());

        if(userMapper.exists(queryWrapper)){
            throw new BoloException(ResultCodeEnum.ACCOUNT_EXIST_ERROR);
        }

        // 3. 添加到数据库
        User newUser = new User();
        newUser.setUsername(loginVo.getName());
        // 设置默认昵称
        newUser.setNickname("bolo_" + UUID.randomUUID());
        newUser.setPassword(DigestUtils.md5Hex(loginVo.getPassword()));
        int insert = userMapper.insert(newUser);
        if(insert == 0){
            log.error("数据库错误：插入数据错误");
            throw new BoloException(ResultCodeEnum.SERVER_ERROR);
        }

    }
}
