/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Controller;

import com.example.TravelinVServer.Modal.Post;
import com.example.TravelinVServer.RequestsDTO.DescriptionPayload;
import com.example.TravelinVServer.Responese.DescriptionResponse;
import com.example.TravelinVServer.Service.AzureBlobStorageService;
import com.example.TravelinVServer.Service.DescriptionService;
import com.example.TravelinVServer.Service.PostService;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping(value = "/description")
public class DescriptionController {

    @Autowired
    private DescriptionService descriptionService;
    @Autowired
    private PostService postService;
    @Autowired
    private AzureBlobStorageService azureBlobStorageService;

    @GetMapping("/public/{id_post}")
    public ResponseEntity<List<DescriptionResponse>> getDescriptionFromID(@PathVariable("id_post") Integer id_post) {
        try {
            List<DescriptionResponse> res = descriptionService.handleGetDescriptionInfoFromPostId(id_post);
            if (res != null) {
                return ResponseEntity.ok(res);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/createDes")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> createDescription(
            @RequestPart(value = "description") DescriptionPayload description,
            @RequestPart(value = "DesImage1", required = false) MultipartFile DesImage1,
            @RequestPart(value = "DesImage2", required = false) MultipartFile DesImage2
    ) throws ServletException, IOException, Exception {
        String des_image1 = null;
        String des_image2 = null;
        if (DesImage1 != null) {
            des_image1 = azureBlobStorageService.uploadImage(DesImage1);
        }
        if (DesImage2 != null) {
            des_image2 = azureBlobStorageService.uploadImage(DesImage2);
        }
        try {
            Post post = postService.handleGetPostByID(description.getId_post());
            descriptionService.createDes(description, post, des_image1, des_image2);
            return ResponseEntity.ok("Description create successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
}
