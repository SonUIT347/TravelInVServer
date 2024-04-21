/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Responese;

import com.example.TravelinVServer.Modal.Province;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class RelatedPostResponse extends FeaturedPostResponse{
    
    public RelatedPostResponse(Integer id_post, Date date_time, String post_name, String image, Province province) {
        super(id_post, date_time, post_name, image, province);
    }
    
}
