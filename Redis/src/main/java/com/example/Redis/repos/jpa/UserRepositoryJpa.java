package com.example.Redis.repos.jpa;


import com.example.Redis.entities.jpa.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserJpa,Long> {
}