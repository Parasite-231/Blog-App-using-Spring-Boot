package com.blog.app.service.blog;

import com.blog.app.model.blog.BlogModel;

import java.util.List;

public interface BlogService {

    public List<BlogModel> getAllBlogPost();

    public BlogModel postBlog(BlogModel blog);
//
//    public List<BlogModel>  getAllBlogByUserId(Long userId);
}
