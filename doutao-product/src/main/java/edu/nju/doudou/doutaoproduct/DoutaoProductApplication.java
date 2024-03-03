package edu.nju.doudou.doutaoproduct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableCaching
@EnableDiscoveryClient
@MapperScan("edu.nju.doudou.doutaoproduct.dao")
@SpringBootApplication
public class DoutaoProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoutaoProductApplication.class, args);
    }

}
