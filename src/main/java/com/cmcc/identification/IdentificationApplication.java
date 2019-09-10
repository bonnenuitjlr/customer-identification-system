package com.cmcc.identification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
//@EnableDiscoveryClient
//@NacosPropertySource(dataId = "application-prod.yml", autoRefreshed = true)
public class IdentificationApplication {

    public static void main(String[] args) {

        SpringApplication.run(IdentificationApplication.class, args);

    }

}
