package org.softmaker.userwebapp.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softmaker.userwebapp.model.User;
import org.softmaker.userwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractUserController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll(){
        logger.info("get all users");
        return service.getAll();
    }

    public User get(int id){
        logger.info("get user {}", id);
        return service.get(id);
    }

    public User create(User user){
        logger.info("create user {}", user);
        return service.create(user);
    }

    public void delete(int id){
        logger.info("delete user {}", id);
        service.delete(id);
    }

    public void update(User user){
        logger.info("update user {}", user);
        service.update(user);
    }

    public User getByEmail(String email){
        logger.info("get user by email {}", email);
        return service.getByEmail(email);
    }
}
