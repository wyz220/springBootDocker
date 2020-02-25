package com.wyz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wyz.mapper")  //配置mapper扫描
public class ApplicationDemo {

    public static void main(String[] args){
        SpringApplication.run(ApplicationDemo.class,args);
    }
}
