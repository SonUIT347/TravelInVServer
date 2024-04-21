/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Service;

import com.example.TravelinVServer.Modal.Description;
import com.example.TravelinVServer.Modal.Post;
//import com.example.TravelinVServer.Modal.User;
import com.example.TravelinVServer.Repository.DescriptionRepository;
import com.example.TravelinVServer.Repository.PostRepository;
import com.example.TravelinVServer.RequestsDTO.PostRequest;
import com.example.TravelinVServer.Responese.DescriptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class DescriptionService {

    @Autowired
    private DescriptionRepository descriptionRepository;
    @Autowired
    private PostRepository postRepository;

    public Description createDes(PostRequest postRequest, Post post, String image1, String image2) {
        Description description = new Description();
        description.setDescription(postRequest.getDescription());
        description.setImage1(image1);
        description.setImage2(image2);
        description.setTitle(postRequest.getDes_title());
        description.setPost(post);
        return descriptionRepository.save(description);
    }

    public DescriptionResponse handleGetDescriptionInfoFromPostId(Integer id_post) {
        if (id_post != null) {
//            System.out.println(descriptionRepository.existsById(id_post));
//            if (descriptionRepository.existsById(id_post)) {

            return descriptionRepository.getDescription(id_post);
//            }
        }
        return null;
    }

    public boolean handleDeleteDescriptionByPostId(Integer id_post) {
        try {
            if (postRepository.existsById(id_post)) {
                descriptionRepository.deleteDescritionByPostId(id_post);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
