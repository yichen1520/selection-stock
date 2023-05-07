package com.WangTeng.stock.user.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.WangTeng"})
@RestController
public class StockUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockUserApplication.class,args);
    }

    /**
     * 提供访问测试接口， 支持POST和GET
     **/
    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome selection stock platform! !";
    }
}
