package com.riffhub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.riffhub.mapper")
@EntityScan(basePackages = {"com.riffhub.pojo"})
@EnableJpaRepositories(basePackages = "com.riffhub.mapper")
public class RiffHubApplication
{
    public static void main(String[] args) {
        SpringApplication.run(RiffHubApplication.class,args);
    }
}
