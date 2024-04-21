/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Controller;

import com.example.TravelinVServer.RequestsDTO.SubCommentPayload;
import com.example.TravelinVServer.Service.SubCommentService;
// import com.example.TravelinVServer.Until.Helper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping(value = "/subcomment")
public class SubCommentController {

    @Autowired
    private SubCommentService subCommentService;
    // @Autowired
    // private Helper helper;

    @PostMapping("/create/{id_comment}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> createSubComment(@PathVariable("id_comment") Integer id_comment, @RequestBody SubCommentPayload payload) {
        try {

            subCommentService.handleCreateSubComment(id_comment, payload.getUsername(), payload.getDescription());
            return ResponseEntity.ok("Successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }

    @PutMapping(value = "/update/{id_sub_comment}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> updateSubComment(@PathVariable("id_sub_comment") Integer id_sub_comment, @RequestBody SubCommentPayload payload, HttpServletRequest request) {
        try {
            boolean result = subCommentService.handleUpdateSubComment(id_sub_comment, payload.getDescription(), payload.getUsername());
            if (result) {
                return ResponseEntity.ok("Update suceeded");
            } else {
                return ResponseEntity.badRequest().body("User is not owner of sub comment");
            }

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }

    @DeleteMapping(value = "/delete/{id_sub_comment}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> deleteSubComment(@PathVariable("id_sub_comment") Integer id_sub_comment, @RequestBody SubCommentPayload payload) {
        try {
            boolean result = subCommentService.handleDeleteSubCommentByID(id_sub_comment, payload.getUsername());
            if (result) {
                return ResponseEntity.ok("Delete suceeded");
            } else {
                return ResponseEntity.badRequest().body("User is not owner of sub comment or post");
            }
        } catch (Exception e) {
             return ResponseEntity.internalServerError().body(e.toString());
        }
    }
}
