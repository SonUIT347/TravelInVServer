/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Responese;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class LoginResponse implements Serializable{
    private String jwtToken;
    private int status;
    public String getJwttoken() {
        return jwtToken;
    }

    public int getStatus() {
        return status;
    }
    
    public LoginResponse() {
    }
    
    public void setStatus(int status) {
        this.status = status;
    }

    public void setJwttoken(String jwttoken) {
        this.jwtToken = jwttoken;
    }

    public LoginResponse(String jwtToken, int status) {
        this.jwtToken = jwtToken;
        this.status = status;
    }
    
}
