/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.RequestsDTO;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class LoginRequest implements Serializable{

    private String username;
    private String password;

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public LoginRequest() {
    }

    public LoginRequest(String userName, String password) {
        this.username = userName;
        this.password = password;
    }
    
}
