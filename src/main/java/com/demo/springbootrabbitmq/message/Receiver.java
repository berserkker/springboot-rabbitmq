package com.demo.springbootrabbitmq.message;

import com.demo.springbootrabbitmq.entity.User;
import com.demo.springbootrabbitmq.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Date;

@Component
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
    @Autowired
    private UserService userService;

    @RabbitHandler
    @RabbitListener(queues = "user")
    public void process(byte[] objBytes) {
        User user = (User) getObjectFromBytes(objBytes);
        LOGGER.info("receive message user: " + new Date());
        LOGGER.info("name : " + user.getName());
        //消息队列监听
        userService.create(user);
    }

    /**
     * 字节码转化为对象
     */
    public Object getObjectFromBytes(byte[] objBytes) {
        Object result = null;
        try {
            if (objBytes == null || objBytes.length == 0) {
                return null;
            }
            ByteArrayInputStream bai = new ByteArrayInputStream(objBytes);
            ObjectInputStream ois = new ObjectInputStream(bai);
            result = ois.readObject();
            bai.close();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
