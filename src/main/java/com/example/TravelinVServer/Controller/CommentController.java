/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Controller;

import com.example.TravelinVServer.Modal.Post;
import com.example.TravelinVServer.Modal.User;
import com.example.TravelinVServer.RequestsDTO.CommentPayload;
import com.example.TravelinVServer.Responese.CommentResponse;
import com.example.TravelinVServer.Responese.PostResponse;
import com.example.TravelinVServer.Service.CommentService;
import com.example.TravelinVServer.Service.PostService;
// import com.example.TravelinVServer.Service.UserService;
import com.example.TravelinVServer.Until.Helper;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private Helper helper;
    @Autowired
    private PostService postService;
    // @Autowired
    // private UserService userService;

    @PostMapping("/create/{id_post}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> createComment(
            @PathVariable("id_post") Integer id_post,
            @RequestBody CommentPayload description,
            HttpServletRequest request
    ) {
        try {
            User user = helper.getUserFromToken(request);
            Post post = postService.handleGetPostByID(id_post);
            if (user != null && post != null) {
                commentService.handleCreateComment(post, user, description.getDescription());
                return ResponseEntity.ok("Comment create succesfully");
            }
            return ResponseEntity.badRequest().body("Both user and post is required");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{id_comment}")
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable("id_comment") Integer id_comment) {
        try {
            CommentResponse comment = commentService.handleGetCommentByID(id_comment);
            return ResponseEntity.ok(comment);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/post/{id_post}")
    public ResponseEntity<List<CommentResponse>> getCommentByPostId(@PathVariable("id_post") Integer id_post) {
        try {
            if (postService.handleGetPostByID(id_post) != null && id_post != null) {
                List<CommentResponse> comment = commentService.handleGetCommentByPostID(id_post);
                return ResponseEntity.ok(comment);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/update/{id_comment}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> updateComment(@PathVariable("id_comment") Integer id_comment,@RequestBody CommentPayload payload, HttpServletRequest request) {
        try {
            Integer id_user = helper.getUserFromToken(request).getId_user();
            if(id_user != null && id_user.equals(commentService.handleGetCommentByID(id_comment).getId_user()) && payload.getDescription() != null){
                commentService.handleUpdateComment(id_comment, payload.getDescription());
                return ResponseEntity.ok("Update suceeded");
            }else{
                return ResponseEntity.badRequest().body("User is not owner of comment or description is null");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @DeleteMapping(value = "/delete/{id_comment}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<String> deleteCommentByID(
            @PathVariable("id_comment") Integer id_comment,
            HttpServletRequest request
    ) {
        try {
            ResponseEntity<String> res = null;
            Integer id_user = helper.getUserFromToken(request).getId_user();
            if (id_comment != null) {
                CommentResponse comment = commentService.handleGetCommentByID(id_comment);
                if (comment != null && id_user.equals(comment.getId_user())) {
                    commentService.handleDeleteCommentByID(id_comment);
                    return ResponseEntity.ok("Delete Succeeded");
                } else {
                    res = ResponseEntity.badRequest().body("Delete falled because user id not owner of comment or comment is not exist");
                }
                PostResponse post = commentService.handleGetPostByCommentID(id_comment);
                if (comment != null && id_user.equals(post.getId_user())) {
                    commentService.handleDeleteCommentByID(id_comment);
                    return ResponseEntity.ok("Delete Succeeded");
                } else {
                    res = ResponseEntity.badRequest().body("Delete falled because user id not owner of post or post is not exist");
                }
                return res;
            }
            return ResponseEntity.badRequest().body("id comment is incorect");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }

}
