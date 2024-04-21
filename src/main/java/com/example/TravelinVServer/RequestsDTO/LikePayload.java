/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.RequestsDTO;

/**
 *
 * @author Admin
 */
public class LikePayload {
    private Integer id_user;
    private Integer id_post;

    public LikePayload(Integer id_user, Integer id_post) {
        this.id_user = id_user;
        this.id_post = id_post;
    }

    public LikePayload() {
    }

    public Integer getId_user() {
        return id_user;
    }

    public Integer getId_post() {
        return id_post;
    }
    
}
