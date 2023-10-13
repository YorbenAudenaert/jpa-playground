package com.audenyo.jpaplayground.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.audenyo.jpaplayground.repository"
)
@EnableTransactionManagement
public class JpaConfig {

}
