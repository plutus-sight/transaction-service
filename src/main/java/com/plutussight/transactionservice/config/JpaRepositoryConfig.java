package com.plutussight.transactionservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.plutussight.transactionservice.repository.jpa")
public class JpaRepositoryConfig {
}