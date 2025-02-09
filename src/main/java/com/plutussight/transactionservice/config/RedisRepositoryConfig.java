package com.plutussight.transactionservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(basePackages = "com.plutussight.transactionservice.repository.redis")
public class RedisRepositoryConfig {
}