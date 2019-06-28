package com.cmcc.identification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients //启动feign
public class IdentificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdentificationApplication.class, args);
    }

}
