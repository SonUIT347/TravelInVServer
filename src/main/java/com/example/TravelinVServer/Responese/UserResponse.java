/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Responese;

/**
 *
 * @author Admin
 */
public class UserResponse {

    private Integer id_user;
    private String name;
    private String gender;
    private String phone_number;
    private String email;
    private String account_type;
    private String avatar;
    private String username;
    private String password;
    private String role;

    public UserResponse(Integer id_user, String name, String gender, String phone_number, String email, String account_type, String avatar, String username, String password, String role) {
        this.id_user = id_user;
        this.name = name;
        this.gender = gender;
        this.phone_number = phone_number;
        this.email = email;
        this.account_type = account_type;
        this.avatar = avatar;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserResponse() {
    }

    public Integer getId_user() {
        return id_user;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public String getAccount_type() {
        return account_type;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
