/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Modal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
// import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Set;

/**
 *
 * @author Admin
 */
@Entity(name = "provinces")
@Table(name = "provinces")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_province;
    private String province_name;
    private String description;
    private String image;
    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private Set<Post> posts;

    public Integer getId_province() {
        return id_province;
    }

    public String getProvince_name() {
        return province_name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Set<Post> getPosts() {
        return posts;
    }
    
}
