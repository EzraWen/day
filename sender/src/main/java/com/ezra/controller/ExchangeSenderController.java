package com.ezra.controller;


import com.ezra.constant.RabbitMQConstant;
import com.ezra.constant.SystemConstant;
import com.ezra.dto.MessageHeadersDTO;
import com.ezra.response.Result;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(SystemConstant.API_URL + "/ex")
public class ExchangeSenderController {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 广播发送
     * @return
     */
    @PostMapping("/fanout")
    public Result fanoutSend(@RequestParam("msg") String msg){
        rabbitTemplate.convertAndSend(RabbitMQConstant.FANOUT_EXCHANGE,null,msg);
        return Result.SUCCESS;
    }

    /**
     * 路由全匹配
     */
    @PostMapping("/direct")
    public Result directSend(@RequestParam("msg") String msg,@RequestParam("key") String key){
        rabbitTemplate.convertAndSend(RabbitMQConstant.DIRECT_EXCHANGE,key,msg);
        return Result.SUCCESS;
    }

    /**
     * 通配符匹配
     * *匹配一个单词字符
     * #匹配多个单词字符
     */
    @PostMapping("/topic")
    public Result topicSend(@RequestParam("msg") String msg,@RequestParam("key") String key){
        rabbitTemplate.convertAndSend(RabbitMQConstant.TOPIC_EXCHANGE,key,msg);
        return Result.SUCCESS;
    }


    /**
     * headers匹配
     */
    @PostMapping("/headers")
    public Result headersSend(@RequestBody MessageHeadersDTO dto, @RequestParam("msg") String msg){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("name",dto.getName());
        messageProperties.setHeader("bindType",dto.getBindType());
        rabbitTemplate.convertAndSend(RabbitMQConstant.HEADERS_EXCHANGE,null,new Message(msg.getBytes(),messageProperties));
        return Result.SUCCESS;
    }
}
