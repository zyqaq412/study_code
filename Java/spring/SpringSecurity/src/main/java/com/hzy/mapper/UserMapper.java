package com.hzy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzy.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @title: UserMapper
 * @Author zxwyhzy
 * @Date: 2022/12/19 19:02
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
