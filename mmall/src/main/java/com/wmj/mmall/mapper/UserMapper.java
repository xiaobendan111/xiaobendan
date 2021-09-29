package com.wmj.mmall.mapper;

import com.wmj.mmall.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 菜啊
 * @since 2021-04-19
 */

@Repository
public interface UserMapper extends BaseMapper<User> {

}
