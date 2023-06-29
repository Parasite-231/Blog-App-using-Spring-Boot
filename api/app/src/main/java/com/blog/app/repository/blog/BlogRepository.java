package com.blog.app.repository.blog;

import com.blog.app.model.blog.BlogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<BlogModel, Long> {
}
