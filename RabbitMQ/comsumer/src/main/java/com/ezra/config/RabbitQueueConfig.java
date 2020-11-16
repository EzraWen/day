package com.ezra.config;

import com.ezra.constant.RabbitMQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMQ 配置
 */
@Configuration
public class RabbitQueueConfig {

    public Queue queue1() {
        return new Queue(RabbitMQConstant.QUEUE1);
    }

    @Bean
    public Queue queue2() {
        return new Queue(RabbitMQConstant.QUEUE2);
    }


    /**
     * 以下是通配符模式的配置
     */

    @Bean
    public Queue topic1() {
        return new Queue("topic1");
    }

    @Bean
    public Queue topic2() {
        return new Queue("topic2");
    }

    @Bean
    public Queue topic3() {
        return new Queue("topic3");
    }

    @Bean
    public Queue topic4() {
        return new Queue("topic4");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitMQConstant.TOPIC_EXCHANGE);
    }


    @Bean
    public Binding binding1(@Qualifier("topic1") Queue queue,
                            @Qualifier("topicExchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("a.#");
    }

    @Bean
    public Binding binding2(@Qualifier("topic2") Queue queue,
                            @Qualifier("topicExchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("a.*");
    }

    @Bean
    public Binding binding3(@Qualifier("topic3") Queue queue,
                            @Qualifier("topicExchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("*.a");
    }

    @Bean
    public Binding binding4(@Qualifier("topic4") Queue queue,
                            @Qualifier("topicExchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("#.a");

    }


    /**
     * 以下是headers模式的配置
     */

    @Bean
    public Queue headers1() {
        return new Queue("headers1");
    }

    @Bean
    public Queue headers2() {
        return new Queue("headers2");
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(RabbitMQConstant.HEADERS_EXCHANGE);
    }

    @Bean
    public Binding headersBinding1() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "q1");
        map.put("bindType", "all");
        return BindingBuilder.bind(headers1()).to(headersExchange()).whereAll(map).match();
    }

    @Bean
    public Binding headersBinding2() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "q2");
        map.put("bindType", "any");
        return BindingBuilder.bind(headers2()).to(headersExchange()).whereAny(map).match();
    }
}
