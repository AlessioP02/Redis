package com.example.Redis.repos.redis;

import com.example.Redis.entities.redis.UserRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryRedis extends CrudRepository<UserRedis,Long> {
}