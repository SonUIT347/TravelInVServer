/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Service;

import com.example.TravelinVServer.Modal.Comment;
import com.example.TravelinVServer.Modal.SubComment;
import com.example.TravelinVServer.Modal.User;
import com.example.TravelinVServer.Repository.CommentRepository;
import com.example.TravelinVServer.Repository.SubCommentRepository;
import com.example.TravelinVServer.Repository.UserRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class SubCommentService {

    @Autowired
    private SubCommentRepository subCommentRepository;
    @Autowired
    private CommentRepository commentRespository;
    @Autowired
    private UserRepository userReository;

    public String handleCreateSubComment(Integer id_comment, String username, String description) {
        SubComment subcomment = new SubComment();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        Optional<Comment> comment = commentRespository.findById(id_comment);
        User user = userReository.findByUsername(username);
        try {
            subcomment.setComment(comment.get());
            subcomment.setContent(description);
            subcomment.setDate_time(date);
            subcomment.setUser(user);
            subCommentRepository.save(subcomment);
            return "Create sub_comment succeeded";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean handleUpdateSubComment(Integer id_sub_comment, String description, String username) {
        try {
            Integer id_user = userReository.findByUsername(username).getId_user();
            Integer id_subcomment_owner = subCommentRepository.findById(id_sub_comment).get().getUser().getId_user();
            if (id_user.equals(id_subcomment_owner) && subCommentRepository.existsById(id_sub_comment)) {
                subCommentRepository.editSubComment(id_sub_comment, description);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean handleDeleteSubCommentByID(Integer id_sub_comment, String username) {
        try {
            Integer id_user = userReository.findByUsername(username).getId_user();
            Integer id_subcomment_owner = subCommentRepository.findById(id_sub_comment).get().getUser().getId_user();
            if (subCommentRepository.existsById(id_sub_comment)) {
                subCommentRepository.deleteById(id_sub_comment);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean handleDeleteSubCommnetByCommentID(Integer id_comment){
        try {
            if(commentRespository.existsById(id_comment)){
                subCommentRepository.deleteSubCommentByCommentID(id_comment);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
