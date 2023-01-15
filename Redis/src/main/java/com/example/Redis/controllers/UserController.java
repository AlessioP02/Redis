package com.example.Redis.controllers;

import com.example.Redis.entities.User;
import com.example.Redis.entities.jpa.UserJpa;
import com.example.Redis.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping
    public User create(@RequestBody UserJpa user){
        return userService.create(user);
    }
    @GetMapping
    public List<UserJpa> readAll(){
        return (List<UserJpa>) userService.readAll();
    }
    @GetMapping("/{id}")
    public User readOne(@PathVariable Long id){
        return userService.readeOne(id);
    }
    @GetMapping("/{id}/fast")
    public void readOneFast(@PathVariable Long id){
        userService.readeOneFast(id);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UserJpa user){
        userService.update(id,user);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }
}