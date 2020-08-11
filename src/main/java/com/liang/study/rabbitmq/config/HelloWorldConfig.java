package com.liang.study.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: liang
 * @Date: 2020/8/5 14:47
 */
@Configuration
public class HelloWorldConfig {
    private String HELLO_WORLD_QUEUE = "hello_queue";

    @Bean
    Queue queue(){
        return new Queue(HELLO_WORLD_QUEUE);
    }
}
