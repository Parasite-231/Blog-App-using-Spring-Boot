package com.blog.app.service.user;

import com.blog.app.model.user.UserModel;

import java.util.List;

public interface UserService {

    public void addUser(UserModel user);

    public List<UserModel> getAllUser();

    public UserModel getSearchedUser(Long userId);

    public String deleteUser(long userId);

    public UserModel editUser (Long userId,UserModel updatedUserModel );

//    public UserModel getUserById(Long userId);



}
