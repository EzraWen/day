package com.ezra.receiver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 通配符模式,具体队列映射在rabbitQueueConfig类,需要通过java bean形式建立映射
 */
@Component
public class TopicExchangeRecevier {


   @RabbitListener(queues = "topic1")
    public void topic1(Channel channel, Message message) throws IOException {

        System.out.println("topic1 a.#匹配: " + new String(message.getBody(),"utf-8"));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }



    @RabbitListener(queues = "topic2")
    public void topic2(Channel channel, Message message) throws IOException {

        System.out.println("topic2 a.*匹配: " + new String(message.getBody(),"utf-8"));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }



    @RabbitListener(queues = "topic3")
    public void topic3(Channel channel, Message message) throws IOException {

        System.out.println("topic3 *.a匹配: " + new String(message.getBody(),"utf-8"));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }



    @RabbitListener(queues = "topic4")
    public void topic4(Channel channel, Message message) throws IOException {

        System.out.println("topic4 #.a匹配: " + new String(message.getBody(),"utf-8"));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
