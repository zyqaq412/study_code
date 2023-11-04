package com.hzy.message.controller;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: MessageController
 * @Author zxwyhzy
 * @Date: 2023/11/4 19:44
 * @Version 1.0
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @GetMapping
    public String message(String msg){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=8ed0343a053738748689dce2ace705c20f07c8c6bdf2b67a018cbd6adb981a20");
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        StringBuilder sb = new StringBuilder();
        sb.append("留言");
        sb.append(msg);
        text.setContent(sb.toString());
        request.setText(text);
        try {
            OapiRobotSendResponse response = client.execute(request);
            if (response.getMsg().equals("ok")){
                return "留言成功:";
            }else {
                return "留言失败:"+response.getErrmsg();
            }
        } catch (ApiException e) {
            return "留言失败:"+e.getErrMsg();
        }
    }
}
