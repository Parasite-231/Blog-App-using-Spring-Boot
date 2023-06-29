package com.blog.app.service.user;

import com.blog.app.model.user.UserModel;
import com.blog.app.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(UserModel user) {

        userRepository.save(user);

    }

    @Override
    public void getUser() {

        userRepository.findAll();

    }
}
