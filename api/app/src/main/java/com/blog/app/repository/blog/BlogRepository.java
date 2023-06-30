package com.blog.app.repository.blog;

import com.blog.app.model.blog.BlogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<BlogModel, Long> {


    @Query(nativeQuery = true,value = "SELECT * FROM blog ")
    public  List<BlogModel> findAllBlogs();

//    @Query(nativeQuery = true, value = "SELECT * FROM user INNER JOIN blog ON user.user_id = blog.fk_user_id")
//    List<BlogModel> findBlogsWithUser();
//@Modifying
//@Query(value = "INSERT INTO blog (fk_user_id, blog_post) VALUES (:userId, :blogPost)", nativeQuery = true)
//void insertBlogPost(@Param("userId") Long userId, @Param("blogPost") String blogPost);





}
