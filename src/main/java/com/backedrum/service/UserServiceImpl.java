package com.backedrum.service;

import com.backedrum.model.User;
import com.backedrum.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;

@Service
@Named("userService")
public class UserServiceImpl {

    private UserRepository repository;

    @Inject
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public User findUser(String username) {
        return repository.findUser(username);
    }
}
