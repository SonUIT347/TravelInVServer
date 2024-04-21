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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Admin
 */
@Entity(name ="comments")
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_comment;

    @Column(columnDefinition = "TEXT")
    private String description;
    private Date date_time;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_post")
    private Post post;
    @OneToMany(mappedBy = "comment")
    private Set<SubComment> sub_comment;
//    @OneToOne(mappedBy = "comment", cascade = CascadeType.ALL)
//    private Post post;
//    @OneToOne
//    @JoinColumn(name = "id_post", nullable = false)
//    private Post post;

    public Comment() {
    }

    public Comment(Integer id_comment, String description, Date date_time, User user, Post post, Set<SubComment> sub_comment) {
        this.id_comment = id_comment;
        this.description = description;
        this.date_time = date_time;
        this.user = user;
        this.post = post;
        this.sub_comment = sub_comment;
    }

    public Integer getId_comment() {
        return id_comment;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate_time() {
        return date_time;
    }

    public User getUser() {
        return user;
    }

    public Post getPost() {
        return post;
    }

    public Set<SubComment> getSub_comment() {
        return sub_comment;
    }

    public void setId_comment(Integer id_comment) {
        this.id_comment = id_comment;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setSub_comment(Set<SubComment> sub_comment) {
        this.sub_comment = sub_comment;
    }
    
}
