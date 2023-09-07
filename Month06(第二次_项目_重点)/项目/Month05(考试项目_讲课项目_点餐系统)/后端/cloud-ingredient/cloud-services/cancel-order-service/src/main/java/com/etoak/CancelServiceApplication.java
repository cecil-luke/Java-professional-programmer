package com.etoak;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author Lushisan
 * @Date 2023/6/26 18:58
 * @Title: CancelServiceApplication
 * @Description:
 * @Package com.etoak
 */
@SpringBootApplication
@MapperScan(basePackages = "com.etoak.**.mapper")
@EnableTransactionManagement
public class CancelServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CancelServiceApplication.class, args);
    }
}
