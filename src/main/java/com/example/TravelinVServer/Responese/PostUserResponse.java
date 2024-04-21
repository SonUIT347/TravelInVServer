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
public class PostUserResponse extends PostResponse{
    private String province_name;

    public PostUserResponse(String province_name, Integer id_post, Date date_time, String post_name, String image, String demo_description, String status, Integer id_user, Integer id_province) {
        super(id_post, date_time, post_name, image, demo_description, status, id_user, id_province);
        this.province_name = province_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }
    
}
