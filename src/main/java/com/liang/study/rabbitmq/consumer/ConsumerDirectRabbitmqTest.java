package com.liang.study.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: liang
 * @Date: 2020/8/4 16:17
 */
@Slf4j
@Component
public class ConsumerDirectRabbitmqTest {

    @RabbitListener(queues = "study_test_queue")
    public void consumerDirectQueue(Message message){
        log.info("【消费Rabbitmq消息Direct模式】，message={},queue={}",message,"study_test_queue");
    }

    @RabbitListener(queues = "study_test_queue_topic_1")
    public void consumerDirectQueueTopic1(Message message){
        log.info("【消费Rabbitmq消息Topic1模式】，message={},queue={}",message,"study_test_queue");
    }
    @RabbitListener(queues = "study_test_queue_topic_2")
    public void consumerDirectQueueTopic2(Message message){
        log.info("【消费Rabbitmq消息Topic2模式】，message={},queue={}",message,"study_test_queue");
    }


}
