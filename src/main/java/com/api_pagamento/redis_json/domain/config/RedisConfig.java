package com.api_pagamento.redis_json.domain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPooled;


@Configuration
public class RedisConfig {

    private final String host;
    private final int port;

    //https://stackoverflow.com/questions/28636060/spring-value-often-null
    public RedisConfig(@Value("${spring.redis.host}") String h, @Value("${spring.redis.port}") int p) {
        this.host = h;
        this.port = p;
        System.out.println(host + port);
    }

    @Bean
    public JedisPooled getJedis(){
        System.out.println(host + port);
        return new JedisPooled(host, port);
    }

}
