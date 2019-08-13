package com.demo.springbootrabbitmq;

import com.demo.springbootrabbitmq.service.UserService;
import com.demo.springbootrabbitmq.service.reflect.ReflectService;
import com.demo.springbootrabbitmq.service.reflect.ReflectServiceImpl;
import com.demo.springbootrabbitmq.service.reflect.SiteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private ReflectService reflectService;

    @Test
    public void contextLoads() {
        System.out.println(reflectService.test());

        mockSiteService(reflectService);

        System.out.println(reflectService.test());

    }


    //    @Test
    public void testConsul() {
        System.out.println(loadBalancer.choose("write").getUri().toString());
    }

    private void mockSiteService(ReflectService reflectService) {
        try {
            Field field = ReflectServiceImpl.class.getDeclaredField("siteService");
            field.setAccessible(true);
            field.set(reflectService, buildSiteService("我的测试"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public SiteService buildSiteService(String start) {
        SiteService siteService = name -> start + " " + name;
        return siteService;
    }


}
