package com.ezra.receiver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Headers模式,具体队列映射在rabbitQueueConfig类,需要通过java bean形式建立映射
 */
@Component
public class HeadersExchangeRecevier {


    @RabbitListener(queues = "headers1")
    public void headers1(Channel channel, Message message) throws IOException {
        MessageProperties messageProperties = message.getMessageProperties();
        Map<String, Object> headers = messageProperties.getHeaders();
        System.out.println("headers1 all匹配： " + new String(message.getBody(), "utf-8"));
        System.out.println("headers1 headers: " + headers);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


    @RabbitListener(queues = "headers2")
    public void headers2(Channel channel, Message message) throws IOException {
        MessageProperties messageProperties = message.getMessageProperties();
        Map<String, Object> headers = messageProperties.getHeaders();
        System.out.println("headers2 any匹配： " + new String(message.getBody(), "utf-8"));
        System.out.println("headers2 headers: " + headers);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
