/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.TravelinVServer.Repository;

import com.example.TravelinVServer.Modal.Province;
import com.example.TravelinVServer.Responese.PostResponse;
import com.example.TravelinVServer.Responese.ProvinceResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer>{
    Optional<Province> findById(Integer id);
//    boolean existsProvinceById(Integer id_province);
    @Query("SELECT new com.example.TravelinVServer.Responese.PostResponse(p.id_post, p.date_time, p.post_name, p.image, p.demoDescription, p.status, p.user.id_user, p.province.id_province, p.user.username, p.province.province_name) "
            + "FROM posts p "
            + "WHERE p.status = 'Approved' "
            + "AND p.province.id_province = :id_province")
    List<PostResponse> getPostByProvinceId(@Param("id_province") Integer id_province);
    @Query("SELECT new com.example.TravelinVServer.Responese.ProvinceResponse(p2.id_province, p2.province_name, p2.description, p2.image) "
            + "FROM posts p, provinces p2 "
            + "WHERE p.province.id_province = p2.id_province "
            + "AND p2.id_province != :id_province "
            + "AND p.status = 'Approved' "
            + "GROUP BY p2.id_province "
            + "ORDER BY COUNT(*) DESC")
    List<ProvinceResponse> getDestination(@Param("id_province") Integer id_province);
}
