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
 * 消息通过交换机广播
 */
@Component
public class FanoutExchangeRecevier {


    /**
     * value 是队列
     * exchange 是交换机
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "fanou1",autoDelete = "true"),
            exchange = @Exchange(RabbitMQConstant.FANOUT_EXCHANGE)
    ))
    public void fanout1(Channel channel, Message message) throws IOException {

        System.out.println("fanout1 : " + new String(message.getBody(),"utf-8"));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }


    /**
     * value 是队列
     * exchange 是交换机
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "fanou2",autoDelete = "true"),
            exchange = @Exchange(RabbitMQConstant.FANOUT_EXCHANGE)
    ))
    public void fanout2(Channel channel, Message message) throws IOException {

        System.out.println("fanout2 : " + new String(message.getBody(),"utf-8"));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }


}
