/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.RequestsDTO;

/**
 *
 * @author Admin
 */
public class DescriptionPayload {
    private String description;
    private String des_title;
    private Integer id_post;

    public DescriptionPayload(String description, String des_title, Integer id_post) {
        this.description = description;
        this.des_title = des_title;
        this.id_post = id_post;
    }

    public Integer getId_post() {
        return id_post;
    }

    public void setId_post(Integer id_post) {
        this.id_post = id_post;
    }



    public String getDescription() {
        return description;
    }

    public String getDes_title() {
        return des_title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDes_title(String des_title) {
        this.des_title = des_title;
    }
    
}
