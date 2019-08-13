package com.example.springboot1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication      //核心注解，主要开启自动配置
@MapperScan("com.example.springboot1.mapper")
public class Springboot1Application {


    //标准的main入口，主要作用是项目的启动入口。
    public static void main(String[] args) {
        SpringApplication.run(Springboot1Application.class, args);
    }

}
