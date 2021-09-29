package com.wmj.mmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
// 阻止springboot打开mybitsplus 导致无法单纯的运行启动类
@MapperScan("com.wmj.mmall.mapper")
public class MmallApplication {
    public static void main(String[] args) {
        SpringApplication.run(MmallApplication.class, args);
    }

}
