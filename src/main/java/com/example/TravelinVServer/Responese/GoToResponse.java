/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Responese;

/**
 *
 * @author Admin
 */
public class GoToResponse {
    private String province_name;
    private Integer id_province;

    public GoToResponse() {
    }

    public GoToResponse(String province_name) {
        this.province_name = province_name;
    }
    public GoToResponse(String province_name, Integer id_province) {
        this.province_name = province_name;
        this.id_province = id_province;
    }
    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public Integer getId_province() {
        return this.id_province;
    }

    public void setId_province(Integer id_province) {
        this.id_province = id_province;
    }
    
}
