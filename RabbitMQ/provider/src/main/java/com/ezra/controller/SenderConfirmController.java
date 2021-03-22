package com.ezra.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.ezra.constant.RabbitMQConstant;
import com.ezra.constant.SystemConstant;
import com.ezra.response.Result;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * RabbitMQ confirm return模式的使用案例
 * confirm模式,指消息发送到交换机上的结果确认
 * return模式,指消息从交换机发送到消费者的结果确认
 */
@RestController
@RequestMapping(SystemConstant.API_URL + "/rabbitmq")
public class SenderConfirmController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        /**
         * @param correlationData 消息相关的数据，一般用于获取 唯一标识 id
         * @param b true 消息确认成功，false 失败
         * @param s 确认失败的原因
         */
        @Override
        public void confirm(CorrelationData correlationData, boolean b, String s) {
            if (b) {
                System.out.println("confirm 消息确认成功..." + correlationData != null ? correlationData.getId() : "");
            } else {
                System.out.println("confirm 消息确认失败..." + correlationData.getId() + " cause: " + s);
            }
        }
    };


    private final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        /**
         *  return 的回调方法（找不到路由才会触发）
         * @param message 消息的相关信息
         * @param i 错误状态码
         * @param s 错误状态码对应的文本信息
         * @param s1 交换机的名字
         * @param s2 路由的key
         */
        @Override
        public void returnedMessage(Message message, int i, String s, String s1, String s2) {
            System.out.println(message);
            System.out.println(new String(message.getBody()));
            System.out.println(i);
            System.out.println(s);
            System.out.println(s1);
            System.out.println(s2);
        }
    };

    @PostMapping("/sendConfirm")
    public Result sendMsg(@RequestParam(value = "msg", required = false) String msg) {
        if (StrUtil.isEmpty(msg)) {
            System.out.println("msg为空，不发送到队列中");
            return Result.SUCCESS;
        }

        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        CorrelationData correlationData = new CorrelationData(UUID.fastUUID().toString());
        rabbitTemplate.convertAndSend(RabbitMQConstant.QUEUE1, (Object) msg,correlationData);
        return Result.SUCCESS;
    }


}
