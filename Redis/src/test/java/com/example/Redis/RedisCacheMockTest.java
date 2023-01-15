package com.example.Redis;

import com.example.Redis.entities.redis.UserRedis;
import com.example.Redis.repos.redis.UserRepositoryRedis;
import com.example.Redis.servicies.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisCacheMock.class)
public class RedisCacheMockTest {
    @Autowired
    private UserRepositoryRedis userRepositoryRedis;

    @Autowired
    private UserService userService;

    @Test
    public void shouldWriteOnRedisCache(){
        UserRedis userRedis = new UserRedis();
        userRedis.setDomicileCity("Roma");
        userRedis.setId(1L);
        userRedis.setEmail("prova@gmail.com");
        userRedis.setFirstName("Bob");

        UserRedis userSavedInRedisCache = userRepositoryRedis.save(userRedis);

        Assertions.assertNotNull(userSavedInRedisCache);
    }
    @Test
    public void shouldCreateUser(){
        UserRedis userRedis = new UserRedis();
        userRedis.setDomicileCity("Roma");
        userRedis.setId(1L);
        userRedis.setEmail("prova@gmail.com");
        userRedis.setFirstName("Bob");

        UserRedis userSavedInRedisCache = (UserRedis) userService.create(userRedis);

        Assertions.assertNotNull(userSavedInRedisCache.getId());

    }
    @Test
    public void shouldGetUserFromCache(){
        UserRedis userRedis = new UserRedis();
        userRedis.setDomicileCity("Roma");
        userRedis.setId(1L);
        userRedis.setEmail("prova@gmail.com");
        userRedis.setFirstName("Bob");
        userService.create(userRedis);
        UserRedis userSavedInRedisCache = (UserRedis) userService.readeOne(userRedis.getId());

        Assertions.assertNotNull(userSavedInRedisCache.getId());
    }
    @Test
    public void shouldDeleteUserFromCache(){
        UserRedis userRedis = new UserRedis();
        userRedis.setDomicileCity("Roma");
        userRedis.setId(1L);
        userRedis.setEmail("prova@gmail.com");
        userRedis.setFirstName("Bob");
        userService.create(userRedis);
        userService.delete(userRedis.getId());
        UserRedis userFromCache = userService.convertDataToRedis(userService.readeOne(userRedis.getId()));
        Assertions.assertNull(userFromCache);
    }
    @Test
    public void shouldUpdateUserFromCache(){
        UserRedis userRedis = new UserRedis();
        userRedis.setDomicileCity("Roma");
        userRedis.setId(1L);
        userRedis.setEmail("prova@gmail.com");
        userRedis.setFirstName("MyName");
        userService.create(userRedis);
        userRedis.setDomicileCity("Bob");
        UserRedis userUpdated = userService.convertDataToRedis(userService.update(userRedis.getId(),userService.convertDataToJPA(userRedis)));
        UserRedis userSavedInRedisCache = (UserRedis) userService.readeOne(userUpdated.getId());
        Assertions.assertEquals(userRedis.getDomicileCity(),userSavedInRedisCache.getDomicileCity());
    }
}