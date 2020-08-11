package com.liang.study.rabbitmq.config;

import com.alibaba.druid.sql.visitor.functions.Bin;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author: liang
 * @Date: 2020/8/4 15:24
 */
@Configuration
public class DirectRabbitmqConfig {
    /**
     * 队列名
     */
    private final String STUDY_QUERY = "study_test_queue";
    /**
     * 交换机
     */
    private final String STUDY_EXCHANGE = "study_test_exchange";
    /**
     * routingKey
     */
    private final String STUDY_ROUTING_KEY = "range";


    /**
     * topic 模式队列名
     */
    private final String TOPIC_STUDY_QUERY1 = "study_test_queue_topic_1";
    private final String TOPIC_STUDY_QUERY2 = "study_test_queue_topic_2";
    /**
     * topic 交换机名
     */
    private final String TOPIC_STUDY_EXCHANGE = "study_test_exchange_topic";

    /**
     * 队列名
     * @return
     */
    @Bean
    Queue StudyDirectQueue(){
        return new Queue(STUDY_QUERY,true);
    }

    /**
     * 交换机名
     */
    @Bean
    DirectExchange StudyDirectExchange(){
        return new DirectExchange(STUDY_EXCHANGE,true,false);
    }

    @Bean
    Binding binding(){
        return BindingBuilder.bind(StudyDirectQueue()).to(StudyDirectExchange()).with(STUDY_ROUTING_KEY);
    }

    /**
     * topicQueue1
     * @return
     */
    @Bean
    Queue topicQueue1(){
        return new Queue(TOPIC_STUDY_QUERY1,true);
    }

    /**
     * topicQueue2
     * @return
     */
    @Bean
    Queue topicQueue2(){
        return new Queue(TOPIC_STUDY_QUERY2,true);
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_STUDY_EXCHANGE);
    }

    @Bean
    Binding topicBinding1(){
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("lzc.message");
    }

    @Bean
    Binding topicBinding2(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("lzc.todo");
    }



}
