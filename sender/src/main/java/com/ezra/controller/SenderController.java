package com.ezra.controller;

import com.ezra.constant.RabbitMQConstant;
import com.ezra.response.Result;
import com.ezra.util.ToolUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SenderController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg")
    public Result sendMsg(@RequestParam(value = "msg", required = false) String msg) {
        if (ToolUtil.isEmpty(msg)) {
            System.out.println("msg为空，不发送到队列中");
            return Result.SUCCESS;
        }
        rabbitTemplate.convertAndSend(RabbitMQConstant.QUEUE1,msg);
        return Result.SUCCESS;
    }


    @GetMapping("/sendMsg2")
    public Result sendMsg2(@RequestParam(value = "msg", required = false) String msg) {
        if (ToolUtil.isEmpty(msg)) {
            System.out.println("msg为空，不发送到队列中");
            return Result.SUCCESS;
        }
        rabbitTemplate.convertAndSend(RabbitMQConstant.QUEUE2,msg);
        return Result.SUCCESS;
    }
}
