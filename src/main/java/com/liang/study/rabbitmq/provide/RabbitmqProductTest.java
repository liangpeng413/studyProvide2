package com.liang.study.rabbitmq.provide;

import com.alibaba.fastjson.JSONObject;
import com.liang.study.aop.Log;
import com.liang.study.mode.BdUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: liang
 * @Date: 2020/8/3 18:41
 */
@Slf4j
@Controller
@RequestMapping("/rabbitmq")
public class RabbitmqProductTest {
    @Value("${study.rabbitmq.direct.queue}")
    private String rabbitmqQueue;
    @Value("${study.rabbitmq.topic.queue}")
    private String rabbitmqTopicQueue;
    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    @Log("hhhh")
    @ResponseBody
    @RequestMapping("hhhh")
    public String get() {
        return "hehe";
    }

    @ResponseBody
    @RequestMapping(value = "/sendMessage",method = RequestMethod.GET)
    public String sendRabbitmqTest(){
        String status = "false";
        BdUserInfo bdUserInfo = new BdUserInfo();
        bdUserInfo.setId(1L);
        bdUserInfo.setCountAum(new BigDecimal(1000));
        bdUserInfo.setCreateTime(new Date());
        bdUserInfo.setTotalMoney(new BigDecimal(3000));
        String sendParam = JSONObject.toJSONString(bdUserInfo);
        log.info("【RabbitmqProductTest-sendRabbitmqTest 测试rabbitmq发送】,入参：request={}",sendParam);
        try {
            rabbitmqTemplate.convertAndSend(rabbitmqQueue,sendParam);
            status = "true";
        } catch (AmqpException e) {
            log.error("【RabbitmqProductTest-sendRabbitmqTest 测试rabbitmq发送失败】,入参：request={}",sendParam);
            status = "false";
        }
        return status;
    }

    @ResponseBody
    @RequestMapping(value = "/topic",method = RequestMethod.GET)
    public String sendRabbitmqTopicTest(){
        String status = "false";
        BdUserInfo bdUserInfo = new BdUserInfo();
        bdUserInfo.setId(1L);
        bdUserInfo.setCountAum(new BigDecimal(1000));
        bdUserInfo.setCreateTime(new Date());
        bdUserInfo.setTotalMoney(new BigDecimal(3000));
        String sendParam = null;
        log.info("【RabbitmqProductTest-sendRabbitmqTest topic模式测试rabbitmq发送】,入参：request={}",sendParam);
        try {
            sendParam = JSONObject.toJSONString(bdUserInfo);
            rabbitmqTemplate.convertAndSend(rabbitmqTopicQueue,"lzc.message",sendParam);
            bdUserInfo.setCountAum(new BigDecimal(2000));
            sendParam = JSONObject.toJSONString(bdUserInfo);
            rabbitmqTemplate.convertAndSend(rabbitmqTopicQueue,"lzc.todo",sendParam);
            status = "true";
        } catch (AmqpException e) {
            log.error("【RabbitmqProductTest-sendRabbitmqTest topic模式测试rabbitmq发送失败】,入参：request={}",sendParam);
            status = "false";
        }
        return status;
    }
}
