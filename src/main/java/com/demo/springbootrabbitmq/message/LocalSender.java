package com.demo.springbootrabbitmq.message;

import com.demo.springbootrabbitmq.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

//@Component
public class LocalSender {

    private static final Logger logger = LoggerFactory.getLogger(LocalSender.class);

    @Resource(name = "localRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    public void send(User user) {
        logger.info("发送本地mq,user队列的消息");
        logger.info("get message from user: " + new Date());
        logger.info("name-----" + user.getName());
        this.rabbitTemplate.convertAndSend("user", TranslateObject.getBytesFromObject(user));
    }

}
