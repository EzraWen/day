package com.ezra.receiver;

import com.ezra.constant.RabbitMQConstant;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * MQ 消费者手动确认,消息点对点
 */
@Component
public class StringMsgRecevier {


    @RabbitListener(queues = RabbitMQConstant.QUEUE1)
    public void queue1(String msg, Channel channel) {
        System.out.println("Queue1消费者接收到消息并自动确认：" + msg);
    }

    @RabbitListener(queues = RabbitMQConstant.QUEUE1)
    public void queue12(String msg, Channel channel) {
        System.out.println("Queue12消费者接收到消息并自动确认：" + msg);
    }

    @RabbitListener(queues = RabbitMQConstant.QUEUE2)
    public void queue2(String msg, Channel channel, Message message) {
        System.out.println("消费者接收到消息,需要手动确认：" + msg);
        MessageProperties messageProperties = message.getMessageProperties();
        try {
            Boolean redelivered = messageProperties.getRedelivered();
            System.out.println(redelivered ? "重新投递的信息" : "新消息");
            if (msg.length() > 5 || redelivered) {
                System.out.println("消费");
                channel.basicAck(messageProperties.getDeliveryTag(),false);
                return;
            }else {
                System.out.println("拒绝，重新入列");
                channel.basicNack(messageProperties.getDeliveryTag(),false,true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
