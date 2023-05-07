package com.WangTeng.stock.gateway.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.WangTeng"})
public class StockGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockGatewayApplication.class,args);
    }
}
