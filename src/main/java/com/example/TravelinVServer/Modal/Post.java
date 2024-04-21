/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Modal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Admin
 */
@Entity(name = "posts")
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_post;
    @ManyToOne
    @JoinColumn(name = "id_province")
    private Province province;
    private Date date_time;
    private String image;
    @Column(columnDefinition = "TEXT")
    private String demoDescription;
    private String status;
    private String post_name;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @OneToMany(mappedBy = "post")
    private Set<Comment> comment;
    @OneToMany(mappedBy = "post")
    private Set<Description> description;
    @ManyToMany(mappedBy = "liked_post")
    private Set<User> likes;

    public Post() {
    }

    
    public Post(Integer id_post, Province province, Date date_time, String image, String demoDescription, String status, String post_name, User user, Set<Comment> comment, Set<Description> description, Set<User> likes) {
        this.id_post = id_post;
        this.province = province;
        this.date_time = date_time;
        this.image = image;
        this.demoDescription = demoDescription;
        this.status = status;
        this.post_name = post_name;
        this.user = user;
        this.comment = comment;
        this.description = description;
        this.likes = likes;
    }

    

    
    
    public void setId_post(Integer id_post) {
        this.id_post = id_post;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDemoDescription(String demoDescription) {
        this.demoDescription = demoDescription;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }

    public void setDescription(Set<Description> description) {
        this.description = description;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    public Integer getId_post() {
        return id_post;
    }

    public Province getProvince() {
        return province;
    }

    public Date getDate_time() {
        return date_time;
    }

    public String getImage() {
        return image;
    }

    public String getDemoDescription() {
        return demoDescription;
    }

    public String getStatus() {
        return status;
    }

    public String getPost_name() {
        return post_name;
    }

    public User getUser() {
        return user;
    }

    public Set<Comment> getComment() {
        return comment;
    }

    public Set<Description> getDescription() {
        return description;
    }

    public Set<User> getLikes() {
        return likes;
    }
    
}
