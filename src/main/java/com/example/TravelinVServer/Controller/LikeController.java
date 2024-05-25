/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Controller;

import com.example.TravelinVServer.RequestsDTO.LikePayload;
import com.example.TravelinVServer.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping(value = "/like")
public class LikeController {

    @Autowired
    private LikeService likeService;
//    @Autowired
//    pri

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_COLLABORATOR')")
    public ResponseEntity<String> createLike(@RequestBody LikePayload payload) {
        try {
            if (payload.getId_post() != null && payload.getId_user() != null) {
                boolean result = likeService.handleCreateLike(payload.getId_post(), payload.getId_user());
                return result ? ResponseEntity.ok("Like created successfully") : ResponseEntity.badRequest().body("Like create failed");
            } else {
                return ResponseEntity.badRequest().body("Both id_post and id_user are required");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create like. Please try again later.");
        }
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_COLLABORATOR')")
    public ResponseEntity<String> deleteLike(@RequestBody LikePayload payload) {
        try {
            if (payload.getId_post() != null && payload.getId_user() != null) {
                likeService.handleDeleteLikeById(payload.getId_post(), payload.getId_user());
                return ResponseEntity.ok("Like has been deleted successfully");
            } else {
                return ResponseEntity.badRequest().body("Both id_post and id_user are required");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete like. Please try again later.");
        }

    }

    @DeleteMapping("/delete/{id_post}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_COLLABORATOR')")
    public ResponseEntity<String> deleteAllLikeByPostID(@PathVariable("id_post") Integer id_post) {
        try {
            if (id_post != null) {
                boolean result = likeService.handleDeleteAllLikeByPostID(id_post);
                return result ? ResponseEntity.ok().body("Delete all post has been successfully")
                        : ResponseEntity.badRequest().body("Delete failed");
            }else{
                return ResponseEntity.badRequest().body("ID post is required");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/isLikeExists")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_COLLABORATOR')")
    public ResponseEntity<LikePayload> isLikeExists(@RequestBody LikePayload payload) {
        try {
            if (payload.getId_post() != null && payload.getId_user() != null) {
                if(likeService.handleIsIdExists(payload.getId_post(), payload.getId_user())){
                    return ResponseEntity.ok().body(payload);
                }
                return ResponseEntity.badRequest().body(null);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
