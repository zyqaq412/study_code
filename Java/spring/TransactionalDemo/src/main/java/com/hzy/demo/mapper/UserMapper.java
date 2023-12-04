package com.hzy.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzy.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @title: UserMapper
 * @Author zxwyhzy
 * @Date: 2023/12/4 13:11
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
