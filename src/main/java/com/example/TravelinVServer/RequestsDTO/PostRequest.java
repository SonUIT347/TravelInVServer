/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.RequestsDTO;

// import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public class PostRequest {
    private String title;
    private String demo_description;
    private String status;
    private Integer id_province;
    private String username;
    private String description;
    private String des_title;

    public PostRequest(String title, String demo_description, String status, Integer id_province, String username, String description, String des_title) {
        this.title = title;
        this.demo_description = demo_description;
        this.status = status;
        this.id_province = id_province;
        this.username = username;
        this.description = description;
        this.des_title = des_title;
    }

    public PostRequest(String title, String demo_description, Integer id_province) {
        this.title = title;
        this.demo_description = demo_description;
        this.id_province = id_province;
    }

    public PostRequest(String title, String demo_description) {
        this.title = title;
        this.demo_description = demo_description;
    }
    
    public PostRequest() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDemo_description(String demo_description) {
        this.demo_description = demo_description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId_province(Integer id_province) {
        this.id_province = id_province;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDes_title(String des_title) {
        this.des_title = des_title;
    }

    
    public String getTitle() {
        return title;
    }

    public String getDemo_description() {
        return demo_description;
    }


    public String getStatus() {
        return status;
    }

    public Integer getId_province() {
        return id_province;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public String getDes_title() {
        return des_title;
    }

    
}
