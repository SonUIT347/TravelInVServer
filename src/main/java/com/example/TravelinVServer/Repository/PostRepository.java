/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.TravelinVServer.Repository;

import com.example.TravelinVServer.Modal.Post;
import com.example.TravelinVServer.Modal.Province;
import com.example.TravelinVServer.Responese.AuthorPostResponse;
import com.example.TravelinVServer.Responese.FeaturedPostResponse;
import com.example.TravelinVServer.Responese.GoToResponse;
import com.example.TravelinVServer.Responese.PostResponse;
import com.example.TravelinVServer.Responese.PostUserProvinceResponse;
import com.example.TravelinVServer.Responese.PostUserResponse;
import com.example.TravelinVServer.Responese.RecentPostResponse;
import com.example.TravelinVServer.Responese.RelatedPostResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post save(Post post);

    @Query("SELECT p FROM posts p WHERE p.status = 'Approved'")
    List<Post> getAllPost();

    @Query("SELECT new com.example.TravelinVServer.Responese.FeaturedPostResponse(p.id_post,  p.date_time, p.post_name, p.image, p.province) "
            + "FROM posts p "
            + "WHERE p.status = 'Approved' "
            + "ORDER by p.date_time DESC")
    List<FeaturedPostResponse> getFeaturedPost();

    @Query("SELECT new com.example.TravelinVServer.Responese.RecentPostResponse(p.id_post,  p.date_time, p.post_name, p.image, p.province)"
            + "FROM posts p "
            + "WHERE p.status = 'Approved' "
            + "ORDER by p.date_time DESC")
    List<RecentPostResponse> getRecentPost();

    @Query("SELECT new com.example.TravelinVServer.Responese.RelatedPostResponse (p.id_post, p.date_time, p.post_name,p.image, p.province) "
            + "FROM posts p "
            + "WHERE id_post!=:postId "
            + "AND province=:provinceId "
            + "AND p.status='Approved' "
            + "ORDER by p.date_time DESC")
    List<RelatedPostResponse> getRelatedPost(@Param("postId") Integer postId, @Param("provinceId") Province provinceId);

    @Query(value = "SELECT p.id_post, p.date_time, p.post_name, p.image, pr.province_name, pr.id_province \n"
            + "FROM provinces pr \n"
            + "JOIN posts p ON p.id_province = pr.id_province \n"
            + "WHERE p.id_post IN (\n"
            + "    SELECT p2.id_post \n"
            + "    FROM posts p2 \n"
            + "    JOIN likes l ON p2.id_post = l.id_post \n"
            + "    GROUP BY p2.id_post \n"
            + "    ORDER BY COUNT(*) DESC \n"
            + "    LIMIT 1) ", nativeQuery = true)
    List<Object[]> getPostAndProvince();

    @Query("SELECT new com.example.TravelinVServer.Responese.GoToResponse (p.province_name) "
            + "FROM provinces p, posts p2 "
            + "WHERE p2.province.id_province = p.id_province "
            + "AND p2.status = 'Approved' "
            + "GROUP BY p.id_province")
    List<GoToResponse> getGoto();

    @Query("SELECT new com.example.TravelinVServer.Responese.AuthorPostResponse (u.username, u.avatar, p.id_post, p.date_time, p.post_name, p.image, p.demoDescription, p.status, p.user.id_user, p.province.id_province) "
            + "FROM posts p, Users u "
            + "WHERE p.user.id_user = u.id_user "
            + "AND p.id_post = :id_post")
    List<AuthorPostResponse> getAuthorPost(@Param("id_post") Integer id_post);

    @Query("SELECT new com.example.TravelinVServer.Responese.PostResponse(p.id_post, p.date_time, p.post_name, p.image, p.demoDescription, p.status, p.user.id_user, p.province.id_province, u.username) "
            + "FROM posts p, Users u "
            + "WHERE u.id_user = p.user.id_user "
            + "ORDER BY ("
            + "CASE p.status "
            + "WHEN 'Pending' THEN 1 "
            + "WHEN 'Approved' THEN 2 "
            + "ELSE 100 END) ASC, p.date_time ASC ")
    List<PostResponse> getPostSortByStatusAndDate();

    @Query("SELECT new com.example.TravelinVServer.Responese.PostUserResponse(p2.province_name, p.id_post, p.date_time, p.post_name, p.image, p.demoDescription, p.status, p.user.id_user, p2.id_province) "
            + "FROM posts p, Users u, provinces p2 "
            + "WHERE p.province.id_province = p2.id_province "
            + "AND p.user.id_user = u.id_user "
            + "AND p.status = 'Approved' "
            + "AND u.username = :username")
    List<PostUserResponse> getPostByUsernameApproved(@Param("username") String username);

    @Query("SELECT new com.example.TravelinVServer.Responese.PostUserResponse(p2.province_name, p.id_post, p.date_time, p.post_name, p.image, p.demoDescription, p.status, p.user.id_user, p2.id_province) "
            + "FROM posts p, Users u, provinces p2 "
            + "WHERE p.province.id_province = p2.id_province "
            + "AND p.user.id_user = u.id_user "
            + "AND p.status = 'Pending' "
            + "AND u.username = :username")
    List<PostUserResponse> getPostByUsernamePending(@Param("username") String username);

    @Query("SELECT new com.example.TravelinVServer.Responese.PostUserProvinceResponse(u.username, p2.province_name, p.id_post, p.date_time, p.post_name, p.image, p.demoDescription, p.status, p.user.id_user, p2.id_province) "
            + "FROM posts p, likes l, provinces p2, Users u "
            + "WHERE p.province.id_province = p2.id_province "
            + "AND p.id_post = l.id_post "
            + "AND l.id_user = u.id_user "
            + "AND u.username = :username")
    List<PostUserProvinceResponse> getPostLike(@Param("username") String username);

    @Transactional
    @Modifying
    @Query("UPDATE posts "
            + "SET status = 'Approved' "
            + "WHERE id_post = :id_post ")
    void updatePostStatus(@Param("id_post") Integer id_post);
}
