package com.liang.study.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: liang
 * @Date: 2020/8/13 16:21
 */
@Configuration
public class AckRabbitmqConfig {
    @Value("${study.rabbitmq.ack.queue}")
    private String ACK_QUEUE;

    @Value("${study.rabbitmq.ack.exchange}")
    private String ACK_EXCHANGE;

    @Value("${study.rabbitmq.ack.routing.key}")
    private String ACK_ROUTING_KEY;
    @Bean
    Queue ackQueue(){
        return new Queue(ACK_QUEUE);
    }

    @Bean
    DirectExchange ackExchange(){
        return new DirectExchange(ACK_EXCHANGE,true,false);
    }

    @Bean
    Binding handleBinding(){
        return BindingBuilder.bind(ackQueue()).to(ackExchange()).with(ACK_ROUTING_KEY);
    }

}
