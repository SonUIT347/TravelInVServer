/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.TravelinVServer.Repository;

import com.example.TravelinVServer.Modal.SubComment;
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
public interface SubCommentRepository extends JpaRepository<SubComment, Integer> {

    SubComment save(SubComment subComment);

    @Transactional
    @Modifying
    @Query("UPDATE sub_comments c "
            + "SET c.content = :description "
            + "WHERE c.id_sub_comment = :id_sub_comment")
    void editSubComment(@Param("id_sub_comment") Integer id_sub_comment, @Param("description") String description);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM sub_comments c WHERE c.comment.id_comment = :id_comment")
    void deleteSubCommentByCommentID(@Param("id_comment") Integer id_comment);
}
