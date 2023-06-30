package com.blog.app.service.blog;

import com.blog.app.model.blog.BlogModel;
import com.blog.app.model.user.UserModel;
import com.blog.app.repository.blog.BlogRepository;
import com.blog.app.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImplementation implements BlogService{

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public BlogModel postBlog(BlogModel blog) {
        return  blogRepository.save(blog);

    }

    @Override
    public List<BlogModel> getAllBlogPost() {

        return  blogRepository.findAll();

    }

//    @Override
//    public List<BlogModel> getAllBlogByUserId(Long userId) {
//        Optional<UserModel> user = UserRepository.(userId);
//        return user.map(UserModel::getBlogs).orElse(Collections.emptyList());
//    }


}
