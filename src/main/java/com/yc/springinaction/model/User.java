package com.yc.springinaction.model;

import org.hibernate.annotations.CollectionId;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;

    @NotEmpty(message = "{username cannot be empty.}")
    @Size(min = 3, max = 20)
    @Column(nullable = false, unique = true, length = 20)
    private String userName;

    @NotEmpty(message = "password cannot be empty.")
    @Size(max = 100)
    @Column(length = 100)
    private String password;

    @NotEmpty(message = "name cannot be empty.")
    @Size(min = 2, max = 20)
    @Column(nullable = false, unique = true, length = 20)
    private String name;

    @NotEmpty(message = "email cannot be empty.")
    @Email(message = "wrong email pattern.")
    @Size(max = 50)
    @Column(nullable = false, unique = true, length = 50)
    private  String email;

    @Column(length = 200)
    private String avatar;

    protected User(){

    }

    public User(String userName, String password, String name, String email, String avatar) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.avatar = avatar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
