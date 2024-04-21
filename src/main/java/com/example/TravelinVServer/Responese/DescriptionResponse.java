/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Responese;

/**
 *
 * @author Admin
 */
public class DescriptionResponse {
    private Integer id;
    private String description;
    private String image1;
    private String image2;
    private String title;
    private Integer id_post;

    public DescriptionResponse(Integer id, String description, String image1, String image2, String title, Integer id_post) {
        this.id = id;
        this.description = description;
        this.image1 = image1;
        this.image2 = image2;
        this.title = title;
        this.id_post = id_post;
    }

    public DescriptionResponse() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId_post(Integer id_post) {
        this.id_post = id_post;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }

    public String getTitle() {
        return title;
    }

    public Integer getId_post() {
        return id_post;
    }
    
}
