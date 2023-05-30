package com.hzy.handler;

import com.alibaba.fastjson.JSON;
import com.hzy.domain.ResponseResult;
import com.hzy.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @title: AuthenticationEntryPointImpl
 * @Author zxwyhzy
 * @Date: 2022/12/24 20:26
 * @Version 1.0
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        //ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "认证失败请重新登录");
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), authException.getMessage());
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);
    }
}
