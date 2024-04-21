/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Controller;

/**
 *
 * @author Admin
 */
import com.example.TravelinVServer.Jwt.JwtTokenProvider;
import com.example.TravelinVServer.Modal.User;
import com.example.TravelinVServer.RequestsDTO.UserRequest;
import com.example.TravelinVServer.Service.AzureBlobStorageService;
import com.example.TravelinVServer.Service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AzureBlobStorageService azureBlobStorageService;

    @PostMapping("/addNewUser")
    public ResponseEntity<String> addNewUser(
            @RequestPart UserRequest userRequest,
            @RequestParam("avatar") MultipartFile avatar
    ) throws ServletException, IOException {
        if (userService.isUserExists(userRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User with username " + userRequest.getUsername() + " already exists.");
        }
        try {
            userRequest.setAvatar(azureBlobStorageService.uploadImage(avatar));

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        userRequest.setRole("ROLE_USER");
        String result = userService.addUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> updateUser(
            @RequestPart UserRequest userRequest,
            @RequestParam("avatar") MultipartFile avatar
    ) {
        try {
            if (!userService.isUserExists(userRequest.getUsername())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("User with username " + userRequest.getUsername() + " does not exists.");
            }
            try {
                userRequest.setAvatar(azureBlobStorageService.uploadImage(avatar));

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            boolean result = userService.handleUpdateUser(userRequest);
            if (result) {
                return ResponseEntity.status(HttpStatus.OK).body("update successfully");
            } else {
                return ResponseEntity.badRequest().body("Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> getUserInfo(HttpServletRequest request) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                username = jwtTokenProvider.extractUsername(token);
            } catch (Exception e) {
                System.out.println("error:" + e);
            }
        }
        if (userService.isUserExists(username)) {
            User userInfo = userService.getUserInfo(username);
            return ResponseEntity.ok(userInfo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with username: " + username);
        }
    }

    @GetMapping("/info/{username}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> getOtherUserInfo(@PathVariable String username) {
        if (userService.isUserExists(username)) {
            User userInfo = userService.getUserInfo(username);
            return ResponseEntity.ok(userInfo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with username: " + username);
        }
    }
}
