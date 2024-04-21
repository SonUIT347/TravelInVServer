/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.TravelinVServer.Repository;

import com.example.TravelinVServer.Modal.Description;
import com.example.TravelinVServer.Responese.DescriptionResponse;
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
public interface DescriptionRepository extends JpaRepository<Description, Integer> {

    Description save(Description description);

    @Query("SELECT new com.example.TravelinVServer.Responese.DescriptionResponse(d.id, d.description, d.image1, d.image2, d.title, d.post.id_post) "
            + "FROM descriptions d "
            + "WHERE d.post.id_post = :id_post")
    DescriptionResponse getDescription(@Param("id_post") Integer id_post);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM descriptions d WHERE d.post.id_post = :id_post")
    void deleteDescritionByPostId(@Param("id_post") Integer id_post);
}
