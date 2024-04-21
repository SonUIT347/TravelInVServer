/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Responese;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class CommentResponse {
    private Integer id_comment;
    private Integer id_post;
    private Integer id_user;
    private String description;
    private Date date_time;

    public CommentResponse() {
    }

    public CommentResponse(Integer id_comment, Integer id_post, Integer id_user, String description, Date date_time) {
        this.id_comment = id_comment;
        this.id_post = id_post;
        this.id_user = id_user;
        this.description = description;
        this.date_time = date_time;
    }

    public void setId_comment(Integer id_comment) {
        this.id_comment = id_comment;
    }

    public void setId_post(Integer id_post) {
        this.id_post = id_post;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public Integer getId_comment() {
        return id_comment;
    }

    public Integer getId_post() {
        return id_post;
    }

    public Integer getId_user() {
        return id_user;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate_time() {
        return date_time;
    }
    
}
