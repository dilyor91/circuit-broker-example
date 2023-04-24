package com.example.sbmscircuitbreakerexample01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class SbmsCircuitBreakerExample01Application {

    public static void main(String[] args) {
        SpringApplication.run(SbmsCircuitBreakerExample01Application.class, args);
    }

}
