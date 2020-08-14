package com.liang.study.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;

import java.io.IOException;

/**
 * @author: liang
 * @Date: 2020/8/11 16:28
 */
@Slf4j
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "${study.rabbitmq.ack.queue}",autoDelete = "true"),
        exchange = @Exchange(value = "${study.rabbitmq.ack.exchange}",type = ExchangeTypes.DIRECT),
        key = "${study.rabbitmq.ack.routing.key}"
))
public class AckConsumerRabbitmq {

    @RabbitHandler
    public void consumer(String msg, Channel channel, Message message){
        log.info("【ACK消息确认模式收到消息】,msg={}",msg);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
