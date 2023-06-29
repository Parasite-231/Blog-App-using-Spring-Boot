package com.blog.app.service.user;

import com.blog.app.model.user.UserModel;
import com.blog.app.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(UserModel user) {

        userRepository.save(user);

    }

    @Override
    public UserModel getSearchedUser(Long userId) {
        return userRepository.findById(userId).get();

    }

    @Override
    public List<UserModel> getAllUser() {

        return userRepository.findAll();

    }

    @Override
    public UserModel editUser(Long userId, UserModel updatedUserModel) {
        UserModel user = userRepository.findById(userId).orElse(null);

        if (user != null && updatedUserModel != null) {
            if (updatedUserModel.getUsername() != null) {
                user.setUsername(updatedUserModel.getUsername());
            }
            if (updatedUserModel.getEmail() != null) {
                user.setEmail(updatedUserModel.getEmail());
            }

            user = userRepository.save(user);
        }

        return user;
    }

    @Override
    public String deleteUser(long userId) {
        userRepository.deleteById(userId);
        return null;
    }
}
