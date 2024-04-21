/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Modal;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class LikeId implements Serializable {
    private Integer id_post;
    private Integer id_user;

    // Remember to implement equals() and hashCode() methods

    public LikeId(Integer id_post, Integer id_user) {
        this.id_post = id_post;
        this.id_user = id_user;
    }

    public void setId_post(Integer id_post) {
        this.id_post = id_post;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_post() {
        return id_post;
    }

    public Integer getId_user() {
        return id_user;
    }
    
    public LikeId() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id_post);
        hash = 79 * hash + Objects.hashCode(this.id_user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LikeId other = (LikeId) obj;
        if (!Objects.equals(this.id_post, other.id_post)) {
            return false;
        }
        return Objects.equals(this.id_user, other.id_user);
    }
}
