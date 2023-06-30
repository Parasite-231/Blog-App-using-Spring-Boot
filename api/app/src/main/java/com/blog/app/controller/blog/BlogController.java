package com.blog.app.controller.blog;


import com.blog.app.model.blog.BlogModel;
import com.blog.app.model.user.UserModel;
import com.blog.app.repository.blog.BlogRepository;
import com.blog.app.service.blog.BlogServiceImplementation;
import com.blog.app.service.user.UserService;
import com.blog.app.service.user.UserServiceImplementation;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/blogs")
public class BlogController {
    private  UserService userService;
    private  BlogRepository blogRepository;
    @Autowired
    private BlogServiceImplementation blogService;
    private UserModel userModel;



    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

//    public BlogController(UserService userService) {
//        this.userService = userService;
//    }
//    @GetMapping("/all-blogs")
//    public ResponseEntity<?> getAllBlog() {
//
//            List<BlogModel> blogs = blogService.getAllBlogPost();
//            System.out.println(blogs);
//            return ResponseEntity.status(HttpStatus.OK).body(blogs);
//
//    }


    //One to Many
//    @PostMapping("/post-blog")
//    public ResponseEntity<?> postBlog(@RequestBody BlogModel blog) {
//        Long userId = blog.getUserModel().getUserId(); // user ID nested within the UserModel object
//        UserModel user = userService.getSearchedUser(userId); // getSearchedUser method invoked
//
//        if (user != null) {
//            blog.setUserModel(user);
//            blogService.postBlog(blog);
//            return ResponseEntity.status(HttpStatus.OK).body("Blog added");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//    }

//    @GetMapping("/{userId}")
//    public ResponseEntity<?> getBlogsByUserId(@PathVariable Long userId) {
//        List<UserModel> blogs = blogService.(userId);
//        if (!blogs.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.OK).body(blogs);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No blogs found for the provided user ID.");
//        }
//    }

    @GetMapping("/all-blog")
    public ResponseEntity<List<BlogModel>> getAllBlogs() {
        List<BlogModel> blogs = blogRepository.findAllBlogs();

        if (!blogs.isEmpty()) {
            return ResponseEntity.ok(blogs);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
//    @GetMapping("/bloggers")
//    public ResponseEntity<List<BlogModel>> findBlogsWithUser() {
//        List<BlogModel> blogs = blogRepository.findBlogsWithUser();
//
//        if (!blogs.isEmpty()) {
//            return ResponseEntity.ok(blogs);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }
//    @GetMapping("/{userId}")
//    public ResponseEntity<?> getBlogsByUserId(@PathVariable Long userId) {
//        List<BlogModel> blogs = blogService.getAllBlogByUserId(userId);
//
//        if (!blogs.isEmpty()) {
//            return ResponseEntity.ok(blogs);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No blogs found for the given user ID");
//        }
//    }


}
