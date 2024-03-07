package edu.nju.doudou.doutaoorder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Queue;

@Configuration
public class MyRabbitMQConfig {

    @Bean
    public Queue orderDelayQueue(){

    }
}
