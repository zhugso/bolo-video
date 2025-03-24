package com.zhugso.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhugso.model.entity.User;
import com.zhugso.web.app.mapper.UserMapper;
import com.zhugso.web.app.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author zhugso
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-03-20 01:00:48
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




