package com.ezra.config;

import com.ezra.constant.RabbitMQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
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

    @Bean
    public Queue queue1() {
        /**
         * Queue参数
         * string name          队列名
         * boolean durable      是否持久化   默认为true
         * boolean exclusive    是否排外    只需允许当前连接使用,其他连接不可使用,当前连接关系时清空,不论是否持久化,默认为false
         * boolean autoDelete   是否自动删除 最后一个connection断开时,默认为false
         * Map<String, Object> arguments   构建队列时传递的参数
         * arguments 常用的有
         * x-message-ttl                队列消息ttl
         * x-dead-letter-exchange       队列产生死信后转发的交换机
         * x-dead-letter-routing-key    队列产生死信后转发的路由Key
         */
        return new Queue(RabbitMQConstant.QUEUE1);
    }

    @Bean
    public Queue queue12() {
        return new Queue(RabbitMQConstant.QUEUE1);
    }

    @Bean
    public Queue queue2() {
        return new Queue(RabbitMQConstant.QUEUE2);
    }


    /**
     * 产生死信的队列,只需要队列配置,不需要实际的消费者,这样时间到期后转发给配置的死信队列
     * @return
     */
    @Bean
    public Queue queueDelay() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl",5000);   //5秒后变成死信转发到实际交换机
        arguments.put("x-dead-letter-exchange","exchange_real");  //转发的交换机
        arguments.put("x-dead-letter-routing-key","delayKey");    //转发的key
        return new Queue(RabbitMQConstant.QUEUE3,true,false,false,arguments);
    }

    /**
     * 这下面的配置可以不需要
     */
//    /**
//     *
//     * 创建延迟队列（死信队列）绑定的交换机
//     */
//    @Bean
//    public DirectExchange ttlExchange(){
//        return new DirectExchange("ttlExchange",true,false);
//    }
//
//    /**
//     * 绑定延迟队列（死信队列）到交换机
//     */
//    @Bean
//    public Binding demoTtlBinding(){
//        return new Binding("queueTTL", Binding.DestinationType.QUEUE,"ttlExchange","ttlRoutes",null);
//    }

    @Bean
    public Queue queueReal() {
        return new Queue(RabbitMQConstant.QUEUE4);
    }

    @Bean
    public DirectExchange delayDirectExchange() {
        return new DirectExchange("exchange_real");
    }

    @Bean
    public Binding bindingDelay(@Qualifier("queueReal") Queue queue,
                            @Qualifier("delayDirectExchange") DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with("delayKey");
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
