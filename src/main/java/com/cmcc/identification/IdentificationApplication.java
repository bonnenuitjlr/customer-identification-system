package com.cmcc.identification;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@NacosPropertySource(dataId = "application-prod.yml", autoRefreshed = true)
public class IdentificationApplication {

    public static void main(String[] args) {

        SpringApplication.run(IdentificationApplication.class, args);

    }

}
