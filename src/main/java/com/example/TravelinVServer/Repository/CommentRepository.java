/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.TravelinVServer.Repository;

import com.example.TravelinVServer.Modal.Comment;
import com.example.TravelinVServer.Responese.CommentResponse;
import com.example.TravelinVServer.Responese.PostResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment save(Comment comment);
    
    @Query("SELECT new com.example.TravelinVServer.Responese.CommentResponse(c.id_comment, c.post.id_post, c.user.id_user, description, date_time) "
            + "FROM comments c "
            + "WHERE c.post.id_post = :id_post "
            + "ORDER BY c.date_time ASC")
    List<CommentResponse> getCommentByPostID(@Param("id_post") Integer id_post);
    @Query("SELECT new com.example.TravelinVServer.Responese.PostResponse(p.id_post, p.date_time, p.post_name, p.image, p.status, p.user.id_user, p.province.id_province)"
            + "FROM posts p, comments c "
            + "WHERE p.id_post = c.post.id_post "
            + "AND c.id_comment = :id_comment")
    PostResponse getPostByCommentID(@Param("id_comment") Integer id_comment);
    
    @Transactional
    @Modifying
    @Query("UPDATE comments c "
            + "SET c.description = :description "
            + "WHERE c.id_comment = :id_comment")
    void editComment(@Param("id_comment") Integer id_comment, @Param("description") String description);
}
