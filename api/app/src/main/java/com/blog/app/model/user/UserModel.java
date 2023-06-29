package com.blog.app.model.user;

import com.blog.app.model.blog.BlogModel;

import javax.persistence.*;
import java.util.List;


@Entity
@Table (
        name = "user",
        uniqueConstraints = @UniqueConstraint(
                name = "email",
                columnNames = "email"
        )
)

public class UserModel {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 5
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )


    @Column(
            name = "userId"

    )
    private Long userId;

    @Column(
            name = "username",
            nullable = false
    )
    private String username;
    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private Integer age ;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_userId",referencedColumnName = "userId")
    private List<BlogModel> blogModelList;


}
