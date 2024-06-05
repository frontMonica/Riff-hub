package com.riffhub;

import com.riffhub.utils.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.riffhub.mapper")
@EntityScan(basePackages = {"com.riffhub.pojo"})
@EnableJpaRepositories(basePackages = "com.riffhub.mapper")
@EnableWebSocket
public class RiffHubApplication
{
    public static void main(String[] args) {
        SpringApplication.run(RiffHubApplication.class,args);
    }
    @Bean
    public ServerEndpointExporter serverEndpoint() {
        return new ServerEndpointExporter();
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
