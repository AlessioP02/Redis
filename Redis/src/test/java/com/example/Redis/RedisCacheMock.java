package com.example.Redis;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

@TestConfiguration
public class RedisCacheMock {
    private RedisServer redisServer;
    @Value("${redis.port}") int redisport;

    @PostConstruct
    public void postConstruct(){
        try{
            redisServer = new RedisServer(redisport);
            redisServer.start();
        }catch (Exception e){
            System.out.println(e);
            throw e;
        }
    }
    @PreDestroy
    public void preDestroy(){
        redisServer.stop();
    }

}