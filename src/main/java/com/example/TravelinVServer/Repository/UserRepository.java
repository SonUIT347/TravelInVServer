/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.TravelinVServer.Repository;

import com.example.TravelinVServer.Modal.User;
import com.example.TravelinVServer.RequestsDTO.UserRequest;
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
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    boolean existsByUsername(String username); // Change return type to boolean

    @Transactional
    @Modifying
    @Query("UPDATE Users u "
            + "SET u.name = :#{#userRequest.name}, "
            + "u.email = :#{#userRequest.gmail}, "
            + "u.gender = :#{#userRequest.gender}, "
            + "u.avatar = :#{#userRequest.avatar}, "
            + "u.phone_number = :#{#userRequest.phone_number} "
            + "WHERE u.username = :#{#userRequest.username}")
    void updateUser(@Param("userRequest") UserRequest userRequest);

}
