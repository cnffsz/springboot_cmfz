package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//springboot的入口类
@SpringBootApplication
//扫描dao接口的引入  实现DI
@MapperScan("com.baizhi.dao")
public class AppSpringBoot {
    public static void main(String[] args) {
        //spring 工厂启动
        SpringApplication.run(AppSpringBoot.class,args);
    }
}
