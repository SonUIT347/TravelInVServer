/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.RequestsDTO;

import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class UserRequest {
    private String username;
    private String password;
    private String avatar;
    private String name;
    private String gmail;
    private String role;
    private String gender;
    private String phone_number;

    public UserRequest(String username, String password, String avatar, String name, String gmail, String role, String gender, String phone_number) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.name = name;
        this.gmail = gmail;
        this.role = role;
        this.gender = gender;
        this.phone_number = phone_number;
    }

    public void setPhone_number(String phone_nummber) {
        this.phone_number = phone_nummber;
    }

    public String getPhone_number() {
        return phone_number;
    }


    public UserRequest() {
    }
    

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public String getGmail() {
        return gmail;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
