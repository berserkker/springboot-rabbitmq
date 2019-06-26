package com.demo.springbootrabbitmq.message;

import com.demo.springbootrabbitmq.entity.User;
import com.demo.springbootrabbitmq.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LinuxReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinuxReceiver.class);
    @Autowired
    private UserService userService;

    @RabbitHandler
    @RabbitListener(queues = "user", containerFactory = "linuxFactory")
    public void process(byte[] objBytes) {
        User user = (User) TranslateObject.getObjectFromBytes(objBytes);
        LOGGER.info("收到linux mq的user队列消息" + user.getName());
        LOGGER.info("receive message user: " + new Date());
        LOGGER.info("name : " + user.getName());
        //消息队列监听
        userService.create(user);
    }
}
