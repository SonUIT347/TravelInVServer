/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Service;

import com.example.TravelinVServer.Modal.Like;
import com.example.TravelinVServer.Modal.LikeId;
import com.example.TravelinVServer.Repository.LikeRepository;
import com.example.TravelinVServer.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private PostRepository postRepository;

    public boolean handleIsIdExists(Integer id_post, Integer id_user) {
        if (id_post != null && id_user != null) {
            LikeId id = new LikeId();
            id.setId_post(id_post);
            id.setId_user(id_user);
            try {
                return likeRepository.existsById(id);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean handleCreateLike(Integer id_post, Integer id_user) {
        if (id_post != null && id_user != null && !handleIsIdExists(id_post, id_user)) {
            Like like = new Like();
            like.setId_post(id_post);
            like.setId_user(id_user);
            try {
                likeRepository.save(like);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public void handleDeleteLikeById(Integer id_post, Integer id_user) {
        if (id_post != null && id_user != null) {
            LikeId id = new LikeId();
            id.setId_post(id_post);
            id.setId_user(id_user);
            try {
                likeRepository.deleteById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean handleDeleteAllLikeByPostID(Integer id_post) {
        try {
            if (id_post != null && postRepository.existsById(id_post)) {
                likeRepository.DeleteAllLikeByPostID(id_post);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
