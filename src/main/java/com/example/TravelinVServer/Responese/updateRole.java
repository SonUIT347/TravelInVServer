/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Responese;

/**
 *
 * @author Admin
 */
public class updateRole {
    private String username;
    private String accountType;

    public updateRole(String username, String accountType) {
        this.username = username;
        this.accountType = accountType;
    }

    public updateRole() {
    }

    public String getUsername() {
        return username;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
}
