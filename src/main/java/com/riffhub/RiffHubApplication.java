package com.riffhub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.riffhub.mapper")
public class RiffHubApplication
{
    public static void main(String[] args) {
        SpringApplication.run(RiffHubApplication.class,args);
    }
}
