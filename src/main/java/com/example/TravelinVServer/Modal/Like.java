/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity(name = "likes")
@Table(name = "likes")
@IdClass(LikeId.class)
public class Like {
    @Id
    private Integer id_post;
    @Id
    private Integer id_user;

    public Like(Integer id_post, Integer id_user) {
        this.id_post = id_post;
        this.id_user = id_user;
    }

    public Like() {
    }

    public void setId_post(Integer id_post) {
        this.id_post = id_post;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }
    
    
}
