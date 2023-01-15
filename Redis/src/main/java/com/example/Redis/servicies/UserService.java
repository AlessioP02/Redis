package com.example.Redis.servicies;

import com.example.Redis.entities.User;
import com.example.Redis.entities.jpa.UserJpa;
import com.example.Redis.entities.redis.UserRedis;
import com.example.Redis.repos.jpa.UserRepositoryJpa;
import com.example.Redis.repos.redis.UserRepositoryRedis;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepositoryJpa userRepositoryJpa;

    @Autowired
    private UserRepositoryRedis userRepositoryRedis;

    public UserRedis convertData(UserJpa userJPA){
        UserRedis userRedis = new UserRedis();
        BeanUtils.copyProperties(userJPA, userRedis);
        return userRedis;
    }
    public UserRedis convertDataToRedis(User user){
        UserRedis userRedis = new UserRedis();
        if(userRedis == null) return null;
        if(user == null) return null;
        BeanUtils.copyProperties(user, userRedis);
        return userRedis;
    }
    public UserJpa convertDataToJPA(UserRedis user){
        UserJpa userJPA = new UserJpa();
        if(userJPA == null) return null;
        if(user == null) return null;
        BeanUtils.copyProperties(user, userJPA);
        return userJPA;
    }
    public User create(UserJpa user) {
        if(user == null) return null;
        user.setId(null);
        return userRepositoryJpa.save(user);
    }

    public List<? extends User> readAll() {
        return userRepositoryJpa.findAll();
    }

    public User readeOne(Long id) {
        Optional<UserRedis> userRedis = userRepositoryRedis.findById(id);
        if (userRedis.isPresent()) {
            return userRedis.get();
        } else {
            Optional<UserJpa> userFromDB = userRepositoryJpa.findById(id);
            if(userFromDB.isPresent()){
                userRepositoryRedis.save(convertData(userFromDB.get()));
                return userFromDB.get();
            }
            return null;
        }
    }

    public User update(Long id, UserJpa user) {
        user.setId(id);
        UserJpa userJPA = userRepositoryJpa.save(user);

        Optional<UserRedis> userRedis = userRepositoryRedis.findById(id);
        if(userRedis.isPresent()){
            //userRepositoryRedis.deleteById(id);
            UserRedis userRedis1 = userRepositoryRedis.save(convertDataToRedis(user));
            return userRedis1;
        }
        if(user == null) return null;
        return user;
    }

    public void delete(Long id) {
        Optional<UserJpa> userJPA = userRepositoryJpa.findById(id);
        if(userJPA.isPresent()){userRepositoryJpa.deleteById(id);}
        Optional<UserRedis> userRedis = userRepositoryRedis.findById(id);
        if(userRedis.isPresent())userRepositoryRedis.deleteById(id);
    }

    public void readeOneFast(Long id) {
    }

    public Object create(UserRedis userRedis) {
        if(userRedis == null) return null;
        userRedis.setId(null);
        return userRepositoryRedis.save(userRedis);
    }
}