package com.WangTeng.nacos.config.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: </p>
 * @date  
 * @author   
 * @version 1.0
 * @name   
 * <p>Copyright:Copyright(c)2020</p>
 */
@SpringBootApplication
@RestController
@RefreshScope
public class NacosConfigDemoApplication {

    @Value(value = "${stockName:中国平安}")
    private String stockName;

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigDemoApplication.class, args);
    }

    /**
     * 提供股票名称的访问接口
     * @return
     */
    @RequestMapping("/getStockName")
    public String getStockName() {
        return "股票名称：" + stockName;
    }


}
