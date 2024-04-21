/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Controller;

import com.example.TravelinVServer.Jwt.JwtTokenProvider;
import com.example.TravelinVServer.RequestsDTO.LoginRequest;
import com.example.TravelinVServer.Responese.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateAndGetToken(@RequestBody LoginRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                ));

        if (authentication.isAuthenticated()) {
            String jwtToken = jwtTokenProvider.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(new LoginResponse(jwtToken, HttpStatus.OK.value()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("", HttpStatus.UNAUTHORIZED.value()));
        }
    }
}