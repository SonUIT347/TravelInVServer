/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;
import java.util.Set;

/**
 *
 * @author Admin
 */
@Entity(name = "Users")
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_user;
    private String name;
    private String gender;
    private String phone_number;
    private String email;
    private String account_type;
    private String avatar;
    private String username;
    private String password;
    private String role;

    public String getRole() {
        return role;
    }
    public Integer getId_user() {
        return id_user;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public String getAccount_type() {
        return account_type;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public Set<Comment> getComment() {
        return comment;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public Set<Post> getLiked_post() {
        return liked_post;
    }

    @OneToMany(mappedBy = "user")
    private Set<Post> posts;
    @OneToMany(mappedBy = "user")
    private Set<Comment> comment;
    @ManyToMany
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_post")
    )
    Set<Post> liked_post;

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }

    public void setLiked_post(Set<Post> liked_post) {
        this.liked_post = liked_post;
    }

}
