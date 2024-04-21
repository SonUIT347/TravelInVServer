/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Responese;

import com.example.TravelinVServer.Modal.Province;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class FeaturedPostResponse {

    private Integer id_post;
    private Date date_time;
    private String post_name;
    private String image;
    private Integer id_province;
    private Province province;

    public FeaturedPostResponse(Integer id_post, Date date_time, String post_name, String image, Province province) {
        this.id_post = id_post;
        this.date_time = date_time;
        this.post_name = post_name;
        this.image = image;
        this.province = province;
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

    public Integer getId_province() {
        return this.province.getId_province();
    }
}
