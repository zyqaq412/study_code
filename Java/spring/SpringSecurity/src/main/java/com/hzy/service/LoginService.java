package com.hzy.service;

import com.hzy.domain.ResponseResult;
import com.hzy.domain.entity.User;

public interface LoginService {
    ResponseResult login(User user);
    ResponseResult logout();
}
