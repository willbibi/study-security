package com.kentwen.service;

import org.springframework.stereotype.Service;

/**
 * Created by 毕文 on 2018/12/24.
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String greeting(String name) {
        String result = "hello " + name;
        System.out.println(result);
        return result;
    }
}
