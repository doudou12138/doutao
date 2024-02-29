package edu.nju.doudou.doutaoorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@MapperScan("edu.nju.doudou.doutaoorder.dao")
@SpringBootApplication
public class DoutaoOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoutaoOrderApplication.class, args);
    }

}
