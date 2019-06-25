package com.demo.springbootrabbitmq.message;

import com.demo.springbootrabbitmq.entity.User;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

@Component
public class Sender {

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(User user) {
        logger.info("get message from user: " + new Date());
        logger.info("name-----" + user.getName());
        this.rabbitTemplate.convertAndSend("user", getBytesFromObject(user));
    }

    /**
     * 对象转化为字节码
     */
    public byte[] getBytesFromObject(Serializable obj) {
        byte[] result = null;
        try {
            ByteOutputStream bos = new ByteOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            result = bos.getBytes();
            bos.close();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

}
