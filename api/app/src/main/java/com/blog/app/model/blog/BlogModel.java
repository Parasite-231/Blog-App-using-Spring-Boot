package com.blog.app.model.blog;


import com.blog.app.model.user.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        name = "blog"

)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogModel {


    @Id
    @SequenceGenerator(
            name = "blog_sequence",
            sequenceName = "blog_sequence",
            allocationSize = 5
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "blog_sequence"
    )
    @Column(
            name = "blogId"

    )
    private Long blogId;
    @Column(
            name = "blogPost",
            nullable = false

    )

    private String blogPost;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private String comment;




    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(String blogPost) {
        this.blogPost = blogPost;
    }




//    @ManyToOne
//    @JoinColumn(name = "fk_userId")
//    private UserModel userModel;



}
