/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Until;

import com.example.TravelinVServer.Jwt.JwtTokenProvider;
import com.example.TravelinVServer.Modal.User;
import com.example.TravelinVServer.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class Helper {

    private static final Integer SQL_ROWS_LIMIT_2 = 2;

    private static final Integer SQL_ROWS_LIMIT_3 = 3;

    private static final Integer SQL_ROWS_LIMIT_9 = 9;

    private static final Integer SQL_ROWS_LIMIT_5 = 5;
    private static final Integer SQL_ROWS_LIMIT_1 = 1;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserService userService;

    public Helper() {
    }

    public static Integer getSQL_ROWS_LIMIT_1() {
        return SQL_ROWS_LIMIT_1;
    }

    public static Integer getSQL_ROWS_LIMIT_5() {
        return SQL_ROWS_LIMIT_5;
    }

    public Integer getSQL_ROWS_LIMIT_2() {
        return SQL_ROWS_LIMIT_2;
    }

    public static Integer getSQL_ROWS_LIMIT_3() {
        return SQL_ROWS_LIMIT_3;
    }

    public static Integer getSQL_ROWS_LIMIT_9() {
        return SQL_ROWS_LIMIT_9;
    }

    public User getUserFromToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                username = jwtTokenProvider.extractUsername(token);
            } catch (Exception e) {
                // Log the error
                e.printStackTrace();
            }
        }
        if (username != null) {
            return userService.getUserInfo(username);
        } else {
            return null;
        }
    }
}
