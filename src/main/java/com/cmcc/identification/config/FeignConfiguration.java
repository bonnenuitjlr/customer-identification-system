package com.cmcc.identification.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Configuration
public class FeignConfiguration{
    @Bean
    Logger.Level feignLoggerLevel() {
    	/*
    	 * 记录日志级别；NONE-无记录；
    	 * BASIC-记录方法，URL响应代码和执行时间；
    	 * HEADERS-记录基本信息，请求和标头，
    	 * FULL请求，头文件，正文和元数据
    	 */
        return Logger.Level.FULL;
    }

}