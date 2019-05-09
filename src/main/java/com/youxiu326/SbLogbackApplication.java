package com.youxiu326;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(scanBasePackages = {"com.youxiu326"})
@MapperScan("com.youxiu326.mapper")
@EnableTransactionManagement//开启事务管理
public class SbLogbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbLogbackApplication.class, args);
    }

}
