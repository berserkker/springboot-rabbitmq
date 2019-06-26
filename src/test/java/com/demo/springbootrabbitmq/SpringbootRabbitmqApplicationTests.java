package com.demo.springbootrabbitmq;

import com.demo.springbootrabbitmq.entity.User;
import com.demo.springbootrabbitmq.message.LinuxSender;
import com.demo.springbootrabbitmq.message.LocalSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

    @Autowired
    private LocalSender localSender;

    @Autowired
    private LinuxSender linuxSender;

    @Test
    public void contextLoads() {


        User user2 = User.builder().age(222).name("a22").email("a22@qq.com").build();
        linuxSender.send(user2);


    }

}
