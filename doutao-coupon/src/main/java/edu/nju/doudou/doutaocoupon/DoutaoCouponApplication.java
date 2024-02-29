package edu.nju.doudou.doutaocoupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@MapperScan("edu.nju.doudou.doutaocoupon.dao")
@SpringBootApplication
public class DoutaoCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoutaoCouponApplication.class, args);
    }

}
