package com.phosa.ftms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.phosa.ftms.mapper")
public class FtmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FtmsApplication.class, args);
    }

}
