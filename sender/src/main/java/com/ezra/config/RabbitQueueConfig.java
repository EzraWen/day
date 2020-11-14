package com.ezra.config;

import com.ezra.constant.RabbitMQConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 配置
 */
@Configuration
public class RabbitQueueConfig {

    @Bean
    public Queue queue1(){
        return new Queue(RabbitMQConstant.QUEUE1);
    }

    @Bean
    public Queue queue2(){
        return new Queue(RabbitMQConstant.QUEUE2);
    }
}
