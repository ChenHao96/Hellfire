package com.github.chenhao96;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.github.chenhao96.mapper")
@EnableTransactionManagement
public class HellfireApplication {
    public static void main(String[] args) {
        SpringApplication.run(HellfireApplication.class, args);
    }
}