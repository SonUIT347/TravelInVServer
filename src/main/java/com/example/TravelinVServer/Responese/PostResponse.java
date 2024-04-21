/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Responese;

import com.example.TravelinVServer.Modal.Province;

import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class PostResponse {
    private Integer id_post;
    private Date date_time;
    private String post_name;
    private String image;
    private String demo_description;
    private String status;
    private Integer id_user;
    private Integer id_province;
    private Province province;

    public PostResponse() {
    }

    public PostResponse(Integer id_post, Date date_time, String post_name, String image, String status, Integer id_user, Integer id_province) {
        this.id_post = id_post;
        this.date_time = date_time;
        this.post_name = post_name;
        this.image = image;
//        this.demo_description = demo_description;
        this.status = status;
        this.id_user = id_user;
        this.id_province = id_province;
    }

    public PostResponse(Integer id_post, Date date_time, String post_name, String image, String demo_description, String status, Integer id_user, Integer id_province) {
        this.id_post = id_post;
        this.date_time = date_time;
        this.post_name = post_name;
        this.image = image;
        this.demo_description = demo_description;
        this.status = status;
        this.id_user = id_user;
        this.id_province = id_province;
    }

    public PostResponse(Integer id_post, Date date_time, String post_name, String image, String demo_description, String status, Integer id_user, Province province) {
        this.id_post = id_post;
        this.date_time = date_time;
        this.post_name = post_name;
        this.image = image;
        this.demo_description = demo_description;
        this.status = status;
        this.id_user = id_user;
        this.province = province;
    }
    
    
    
    public void setId_province(Integer id_province) {
        this.id_province = id_province;
    }

    
    public Integer getId_post() {
        return id_post;
    }

    public Date getDate_time() {
        return date_time;
    }

    public String getPost_name() {
        return post_name;
    }

    public String getImage() {
        return image;
    }

    public String getDemo_description() {
        return demo_description;
    }

    public String getStatus() {
        return status;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_post(Integer id_post) {
        this.id_post = id_post;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDemo_description(String demo_description) {
        this.demo_description = demo_description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    
    
    
}
