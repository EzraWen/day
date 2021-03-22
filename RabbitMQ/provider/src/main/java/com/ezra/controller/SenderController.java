package com.ezra.controller;

import cn.hutool.core.util.StrUtil;
import com.ezra.constant.RabbitMQConstant;
import com.ezra.constant.SystemConstant;
import com.ezra.response.Result;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SystemConstant.API_URL + "/rabbitmq")
public class SenderController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/sendMsg")
    public Result sendMsg(@RequestParam(value = "msg", required = false) String msg) {
        if (StrUtil.isEmpty(msg)) {
            System.out.println("msg为空，不发送到队列中");
            return Result.SUCCESS;
        }
        rabbitTemplate.convertAndSend(RabbitMQConstant.QUEUE1, msg);
        return Result.SUCCESS;
    }


    @PostMapping("/sendMsg2")
    public Result sendMsg2(@RequestParam(value = "msg", required = false) String msg) {
        if (StrUtil.isEmpty(msg)) {
            System.out.println("msg为空，不发送到队列中");
            return Result.SUCCESS;
        }
        rabbitTemplate.convertAndSend(RabbitMQConstant.QUEUE2, msg);
        return Result.SUCCESS;
    }

    @PostMapping("/sendMsgDelay")
    public Result sendMsgDelay(@RequestParam(value = "msg", required = false) String msg) {
        if (StrUtil.isEmpty(msg)) {
            System.out.println("msg为空，不发送到队列中");
            return Result.SUCCESS;
        }
        rabbitTemplate.convertAndSend(RabbitMQConstant.QUEUE3, msg);
        return Result.SUCCESS;
    }

}
