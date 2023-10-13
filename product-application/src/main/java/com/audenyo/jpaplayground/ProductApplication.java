package com.audenyo.jpaplayground;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.audenyo")
public class ProductApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ProductApplication.class).run(args);
    }
}
