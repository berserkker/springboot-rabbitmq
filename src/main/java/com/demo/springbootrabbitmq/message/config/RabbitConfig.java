package com.demo.springbootrabbitmq.message.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitConfig {

    @Bean(name = "localConnectionFactory")
    @Primary
    public ConnectionFactory localConnectionFactory(
            @Value("${spring.rabbitmq.local.host}") String host,
            @Value("${spring.rabbitmq.local.port}") int port,
            @Value("${spring.rabbitmq.local.username}") String username,
            @Value("${spring.rabbitmq.local.password}") String password) {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean(name = "linuxConnectionFactory")
    public ConnectionFactory linuxConnectionFactory(
            @Value("${spring.rabbitmq.linux.host}") String host,
            @Value("${spring.rabbitmq.linux.port}") int port,
            @Value("${spring.rabbitmq.linux.username}") String username,
            @Value("${spring.rabbitmq.linux.password}") String password) {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean(name = "localRabbitTemplate")
    @Primary
    public RabbitTemplate localRabbitTemplate(
            @Qualifier("localConnectionFactory") ConnectionFactory connectionFactory
    ) {
        RabbitTemplate localRabbitTemplate = new RabbitTemplate(connectionFactory);
        return localRabbitTemplate;
    }

    @Bean(name = "linuxRabbitTemplate")
    public RabbitTemplate linuxRabbitTemplate(
            @Qualifier("linuxConnectionFactory") ConnectionFactory connectionFactory
    ) {
        RabbitTemplate linuxRabbitTemplate = new RabbitTemplate(connectionFactory);
        return linuxRabbitTemplate;
    }

    @Bean(name = "localFactory")
    public SimpleRabbitListenerContainerFactory localFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("localConnectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean(name = "linuxFactory")
    public SimpleRabbitListenerContainerFactory linuxFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("linuxConnectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public Queue queue() {
        System.out.println("configuration queue ........................");
        return new Queue("user");
    }
}
