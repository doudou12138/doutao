package edu.nju.doudou.doutaothirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DoutaoThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoutaoThirdPartyApplication.class, args);
    }

}
