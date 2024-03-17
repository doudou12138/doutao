package edu.nju.doudou.doutaoorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@EnableRabbit
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("edu.nju.doudou.doutaoorder.dao")
@SpringBootApplication
public class DoutaoOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoutaoOrderApplication.class, args);
    }

}
