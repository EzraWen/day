package com.ezra.receiver;

import com.ezra.constant.RabbitMQConstant;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 路由模式
 */
@Component
public class DirectExchangeRecevier {


    /**
     * value 是队列
     * exchange 是交换机
     * key 是路由键
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "direct1",autoDelete = "true"),
            exchange = @Exchange(value = RabbitMQConstant.DIRECT_EXCHANGE),
            key = {"info","error"}
    ))
    public void direct1(Channel channel, Message message) throws IOException {

        System.out.println("direct1 : " + new String(message.getBody(),"utf-8"));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "direct2",autoDelete = "true"),
            exchange = @Exchange(RabbitMQConstant.DIRECT_EXCHANGE),
            key = "info"
    ))
    public void direct2(Channel channel, Message message) throws IOException {

        System.out.println("direct2 : " + new String(message.getBody(),"utf-8"));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }


}
