package edu.nju.doudou.doutaoware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("edu.nju.doudou.doutaoware.dao")
@SpringBootApplication
public class DoutaoWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoutaoWareApplication.class, args);
    }

}
