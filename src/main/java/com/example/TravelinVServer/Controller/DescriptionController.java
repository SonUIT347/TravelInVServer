/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Controller;

import com.example.TravelinVServer.Responese.DescriptionResponse;
import com.example.TravelinVServer.Service.DescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping(value = "/description")
public class DescriptionController {

    @Autowired
    private DescriptionService descriptionService;

    @GetMapping("/{id_post}")
    public ResponseEntity<DescriptionResponse> getDescriptionFromID(@PathVariable("id_post") Integer id_post) {
        try {
            DescriptionResponse res = descriptionService.handleGetDescriptionInfoFromPostId(id_post);
            if (res != null) {
                return ResponseEntity.ok(res);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
