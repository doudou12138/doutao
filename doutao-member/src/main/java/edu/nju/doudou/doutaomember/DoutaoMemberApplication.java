package edu.nju.doudou.doutaomember;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("edu.nju.doudou.doutaomember.dao")
@SpringBootApplication
public class DoutaoMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoutaoMemberApplication.class, args);
    }

}
