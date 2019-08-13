package com.demo.springbootrabbitmq.controller;

import com.alibaba.fastjson.JSON;
import com.demo.springbootrabbitmq.entity.User;
import com.demo.springbootrabbitmq.message.LocalSender;
import com.demo.springbootrabbitmq.service.UserService;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
//    @Autowired
//    private LocalSender sender;

    private static final AtomicInteger i = new AtomicInteger(40000);

    @RequestMapping(method = RequestMethod.PUT, value = "/message")
    public String inserUser(@RequestBody User user) {
        //调用消息队列
//        sender.send(user);
        return "success";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/data")
    public String inserUserByData(@RequestBody User user) {
        //调用数据库
        userService.create(user);
        return "success";
    }

    /**
     * 模拟调用接口
     */
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(100);
        for (int j = 0; j < 100; j++) {
            Thread thread = new Thread(new SendMessage(latch), "thread :" + i);
            thread.start();
            i.incrementAndGet();
        }
        latch.await();
        System.out.println("Cost: " + (System.currentTimeMillis() - start) + " mills");
    }

    public static class SendMessage implements Runnable {
        private CountDownLatch latch;

        public SendMessage(CountDownLatch latch) {
            this.latch = latch;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            for (int m = 0; m < 50; m++) {
                User user = User.builder().name("xx" + i.get() + m).age(i.get() + m).email(i.get() + m + "@qq.com").build();
                sendPost("http://localhost:9090/user/message", user);
            }
            latch.countDown();
        }
    }

    public static String sendPost(String url, User user) {
        String jsonData = JSON.toJSONString(user);
        OkHttpClient httpClient = new OkHttpClient();
        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonData);
        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .build();
        okhttp3.Response response;
        String result = "";
        try {
            response = httpClient.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
