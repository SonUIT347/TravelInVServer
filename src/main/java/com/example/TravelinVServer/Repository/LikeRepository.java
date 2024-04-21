/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.TravelinVServer.Repository;

import com.example.TravelinVServer.Modal.Like;
import com.example.TravelinVServer.Modal.LikeId;
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
public interface LikeRepository extends JpaRepository<Like, LikeId>{
    Like save(Like like);
    
    void deleteById(LikeId likeId);
    
    boolean existsById(LikeId likeId);
    @Transactional
    @Modifying
    @Query("DELETE FROM likes l WHERE l.id_post = :id_post")
    void DeleteAllLikeByPostID(@Param("id_post") Integer id_post);
}
