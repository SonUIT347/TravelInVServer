/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Service;

import com.example.TravelinVServer.Modal.Comment;
import com.example.TravelinVServer.Modal.Post;
import com.example.TravelinVServer.Modal.User;
import com.example.TravelinVServer.Repository.CommentRepository;
import com.example.TravelinVServer.Responese.CommentResponse;
import com.example.TravelinVServer.Responese.PostResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void handleCreateComment(Post post, User user, String description) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        if (post != null && user != null) {
            Comment comment = new Comment();
            comment.setPost(post);
            comment.setDate_time(date);
            comment.setUser(user);
            comment.setDescription(description);
            commentRepository.save(comment);
        }
    }

    public CommentResponse handleGetCommentByID(Integer id_comment) {
        Optional<Comment> comment = commentRepository.findById(id_comment);
        CommentResponse res = new CommentResponse();
        res.setDate_time(comment.get().getDate_time());
        res.setDescription(comment.get().getDescription());
        res.setId_comment(comment.get().getId_comment());
        res.setId_user(comment.get().getUser().getId_user());
        res.setId_post(comment.get().getPost().getId_post());
        return res;
    }

    public List<CommentResponse> handleGetCommentByPostID(Integer id_post) {
        try {
            List<CommentResponse> comment = commentRepository.getCommentByPostID(id_post);
            return comment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void handleDeleteCommentByID(Integer id_comment) {
        try {
            if (commentRepository.existsById(id_comment)) {
                commentRepository.deleteById(id_comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PostResponse handleGetPostByCommentID(Integer id_comment) {
        try {
            commentRepository.getPostByCommentID(id_comment);
            return commentRepository.getPostByCommentID(id_comment);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean handleUpdateComment(Integer id_comment, String description) {
        try {
            if (commentRepository.existsById(id_comment)) {
                commentRepository.editComment(id_comment, description);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
