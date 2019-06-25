package com.demo.springbootrabbitmq.message.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue userQueue() {
        return new Queue("user");
    }
}
