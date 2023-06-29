package com.blog.app.controller.user;

import com.blog.app.model.user.UserModel;
import com.blog.app.service.user.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserServiceImplementation userService;


    @PostMapping ("/add-user")
    public ResponseEntity<?> addUser(@RequestBody UserModel userModel){
        userService.addUser(userModel);
        return ResponseEntity.status(HttpStatus.OK).body("User Added Successfully");
    }


    @GetMapping("/users")
    public ResponseEntity<?> getAllUser() {
        List<UserModel> users = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getSearchedUser(@PathVariable Long userId) {
        UserModel user = userService.getSearchedUser(userId);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body("User Deleted");

    }

    @PutMapping("/edit-user/{userId}")
    public ResponseEntity<?> editUser(@PathVariable Long userId, @RequestBody UserModel updatedUserModel) {
        UserModel user = userService.editUser(userId, updatedUserModel);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }






}
