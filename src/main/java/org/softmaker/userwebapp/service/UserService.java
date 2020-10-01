package org.softmaker.userwebapp.service;

import org.softmaker.userwebapp.model.User;
import org.softmaker.userwebapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user){
        return repository.save(user);
    }

    public void delete(int id){
        repository.delete(id);
    }

    public User get(int id){
        return repository.get(id);
    }

    public User getByEmail(String email){
        return repository.getByEmail(email);
    }

    public void update(User user){
        repository.save(user);
    }

    public List<User> getAll(){
        return repository.getAll();
    }
}
