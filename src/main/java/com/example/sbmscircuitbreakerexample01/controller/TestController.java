package com.example.sbmscircuitbreakerexample01.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
public class TestController {

    int cnt = 1;
    @GetMapping(value = "/data")
    @HystrixCommand(fallbackMethod = "getDataFromDb",
    commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "3"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "30000"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true")
    })
    public String getDataFromRedis() {
        System.out.println("     ");
        System.out.println("     ");
        System.out.println("     ");
        System.out.println("------------------start request from redis------------------------------------------ " + " cnt " + cnt);
        System.out.println("**getDataFromRedis() method called** " + LocalDateTime.now());
        if(cnt % 2 == 0) { //new Random().nextInt(10) <= 10 &&
            throw new RuntimeException("Redis Server is down");
        }
        //logic to access data from redis
        System.out.println("***************end request from redis*********************" + " cnt " + cnt);
        cnt++;
        return "data accessed from redis (main logic) .... " + LocalDateTime.now() + " cnt " + cnt;
    }

    public String getDataFromDb() {
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("------------------start request from db-------------------------------------------" + " cnt " + cnt);
        System.out.println("**getDataFromDB() method called** " + LocalDateTime.now() + " cnt " + cnt);
        // logic to access data from db
        System.out.println("******************end request from db**********************" + " cnt " + cnt);
        cnt++;
        return "data accessed from database (fall back logic) .... " + LocalDateTime.now() + " cnt " + cnt;
    }
}
