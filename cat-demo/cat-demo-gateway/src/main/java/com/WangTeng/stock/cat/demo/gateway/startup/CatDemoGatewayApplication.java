package com.WangTeng.stock.cat.demo.gateway.startup;

import com.WangTeng.stock.cat.demo.gateway.catutils.CatRestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Transaction;

import java.util.Collections;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.WangTeng"})
@RestController
public class CatDemoGatewayApplication {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 指向订单服务的接口
     */
    @Value("${service2.address:localhost:8082}")
    private String serviceAddress;

    public static void main(String[] args) {
        SpringApplication.run(CatDemoGatewayApplication.class,args);
    }

    /**
     * 模拟一个正常请求
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/gateway")
    public String gateway() throws InterruptedException {
        Thread.sleep(100);
        String response = restTemplate.getForObject("http://" + serviceAddress + "/order", String.class);
        return "gateway response ==>" + response;
    }

    /**
     * 模拟一个请求异常
     * @return
     * @throws Exception
     */
    @RequestMapping("/timeout")
    public String timeout() throws Exception {
        Transaction t = Cat.newTransaction(CatConstants.TYPE_URL, "timeout");
        try {
            Thread.sleep(100);
            String response = restTemplate.getForObject("http://" + serviceAddress + "/timeout", String.class);
            return response;
        }catch(Exception e) {
            Cat.getProducer().logError(e);
            t.setStatus(e);
            throw e;
        }finally {
            t.complete();
        }

    }

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // 保存和传递调用链上下文
        restTemplate.setInterceptors(Collections.singletonList(new CatRestInterceptor()));
        return restTemplate;
    }
}
