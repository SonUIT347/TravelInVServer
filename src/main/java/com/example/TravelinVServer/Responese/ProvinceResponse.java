/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Responese;

/**
 *
 * @author Admin
 */
public class ProvinceResponse {
    private Integer id_province;
    private String province_name;
    private String description;
    private String image;


    public ProvinceResponse() {
    }

    public ProvinceResponse(Integer id_province, String province_name, String description, String image) {
        this.id_province = id_province;
        this.province_name = province_name;
        this.description = description;
        this.image = image;
    }
    
    
    public void setId_province(Integer id_province) {
        this.id_province = id_province;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId_province() {
        return id_province;
    }

    public String getProvince_name() {
        return province_name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
    
}
