package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ishan on 2/6/17.
 */

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.demo")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class ,args);
    }
}
