package edu.nju.doudou.doutaosearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DoutaoSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoutaoSearchApplication.class, args);
    }

}
