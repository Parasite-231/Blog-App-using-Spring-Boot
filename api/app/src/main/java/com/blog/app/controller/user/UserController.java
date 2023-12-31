package com.blog.app.controller.user;

import com.blog.app.model.user.UserModel;
import com.blog.app.repository.user.UserRepository;
import com.blog.app.service.user.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImplementation userService;
 private  UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping ("/add-user")
    public ResponseEntity<?> addUser(@RequestBody UserModel user){
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("User Added Successfully");
    }


    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUser() {
        List<UserModel> users = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{userId}")
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

    @GetMapping("/bloggers")
    public ResponseEntity<List<UserModel>> findBlogsWithUser() {
        List<UserModel> bloggers = userRepository.findBloggers();



        if (!bloggers.isEmpty()) {
            return ResponseEntity.ok(bloggers);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/bloggers/{userId}")
    public ResponseEntity<List<UserModel>> findSpecificBlogFromBlogger(@PathVariable Long userId) {
        List<UserModel> bloggers = userRepository.findSpecificBlogFromBlogger(userId);

        if (!bloggers.isEmpty()) {
            return ResponseEntity.ok(bloggers);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
