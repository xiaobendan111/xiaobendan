package com.wmj.mmall.service.impl;

import com.wmj.mmall.entity.User;
import com.wmj.mmall.mapper.UserMapper;
import com.wmj.mmall.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 菜啊
 * @since 2021-04-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
