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
public class PostUserProvinceResponse extends PostUserResponse{
    private String username;

    public PostUserProvinceResponse(String username, String province_name, Integer id_post, Date date_time, String post_name, String image, String demo_description, String status, Integer id_user, Integer id_province) {
        super(province_name, id_post, date_time, post_name, image, demo_description, status, id_user, id_province);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
