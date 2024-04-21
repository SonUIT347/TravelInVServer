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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;

/**
 *
 * @author Admin
 */
@Entity(name = "sub_comments")
@Table(name = "sub_comments")
public class SubComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_sub_comment;
    @ManyToOne
    @JoinColumn(name = "id_comment")
    private Comment comment;
    private String content;
    private Date date_time;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public SubComment(Integer id_sub_comment, Comment comment, String content, Date date_time, User user) {
        this.id_sub_comment = id_sub_comment;
        this.comment = comment;
        this.content = content;
        this.date_time = date_time;
        this.user = user;
    }

    public SubComment() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

    public Integer getId_sub_comment() {
        return id_sub_comment;
    }

    public Comment getComment() {
        return comment;
    }

    public String getContent() {
        return content;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setId_sub_comment(Integer id_sub_comment) {
        this.id_sub_comment = id_sub_comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

}
