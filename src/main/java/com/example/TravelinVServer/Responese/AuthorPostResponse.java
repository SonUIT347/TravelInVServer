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
public class AuthorPostResponse extends PostResponse{
    private String username;
    private String avatar;

    public AuthorPostResponse() {
    }

    public AuthorPostResponse(String username, String avatar, Integer id_post, Date date_time, String post_name, String image, String demo_description, String status, Integer id_user, Integer id_province) {
        super(id_post, date_time, post_name, image, demo_description, status, id_user, id_province);
        this.username = username;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
}
