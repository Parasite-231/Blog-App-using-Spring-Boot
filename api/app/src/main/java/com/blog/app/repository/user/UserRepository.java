package com.blog.app.repository.user;

import com.blog.app.model.blog.BlogModel;
import com.blog.app.model.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM user ")
    List<UserModel> findBloggers();
    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE user.user_id = :userId")
    List<UserModel> findSpecificBlogFromBlogger(@Param("userId") Long userId);
}
