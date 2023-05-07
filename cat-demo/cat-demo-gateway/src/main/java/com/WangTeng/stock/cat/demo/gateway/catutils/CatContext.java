package com.WangTeng.stock.cat.demo.gateway.catutils;

import com.dianping.cat.Cat;

import java.util.HashMap;
import java.util.Map;

public class CatContext implements Cat.Context {

    /**
     * 存储链路相关信息
     */
    private Map<String,String> properties = new HashMap<>();

    @Override
    public void addProperty(String s, String s1) {
        properties.put(s,s1);
    }

    @Override
    public String getProperty(String s) {
        return properties.get(s);
    }
}
