/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Modal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity(name = "descriptions")
@Table(name = "Descriptions")
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String image1;
    private String image2;
    private String title;
    @ManyToOne
    @JoinColumn(name = "id_post")
    private Post post;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    
    
}
