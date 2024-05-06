/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Service;

import com.example.TravelinVServer.Modal.User;
import com.example.TravelinVServer.Repository.UserRepository;
import com.example.TravelinVServer.RequestsDTO.UserRequest;
import com.example.TravelinVServer.Responese.PostResponse;
import com.example.TravelinVServer.Responese.UserResponse;
import com.example.TravelinVServer.Until.CustomUserDetail;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user from repository
        User user = repository.findByUsername(username);
        // If user not found, throw an exception
        if (user == null) {
//            throw new UsernameNotFoundException("User not found: " + username);
            return null;
        }
        // Create CustomUserDetail object from the retrieved User
        return new CustomUserDetail(user);
    }

    public String addUser(UserRequest userInfo) {
//        userInfo.
        try {
            User user = new User();
            user.setPassword(encoder.encode(userInfo.getPassword()));
            user.setRole(userInfo.getRole());
            user.setEmail(userInfo.getGmail());
//            user.setName(userInfo.getName());
            user.setUsername(userInfo.getUsername());
//            user.setAvatar(userInfo.getAvatar());
            repository.save(user);
            return "User Added Successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<UserResponse> handleGetAllUser() {
        List<User> userList = null;
        try {
            userList = repository.findAll();
            List<UserResponse> postRess = userList
                    .stream()
                    .map(postRes -> {
                        UserResponse res = new UserResponse();
                        res.setId_user(postRes.getId_user());
                        res.setAvatar(postRes.getAvatar());
                        res.setEmail(postRes.getEmail());
                        res.setGender(postRes.getGender());
                        res.setName(postRes.getName());
                        res.setPhone_number(postRes.getPhone_number());
                        res.setRole(postRes.getRole());
                        res.setUsername(postRes.getUsername());
                        return res;
                    })
                    .collect(Collectors.toList());
            return postRess;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean handleUpdateUser(UserRequest userRequest) {
        try {
            repository.updateUser(userRequest);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean handleUpdateRoleUser(String username, String role) {
        try {
            repository.updateUserRoleByUsername(username, role);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isUserExists(String username) {
        return repository.existsByUsername(username);
    }

    public User getUserInfo(String username) {
//        System.out.println(repository.findByUsername(username).getUsername());
        return repository.findByUsername(username);
    }

    public UserResponse getUserData(String username) {
        User info = null;
        try {
            if (repository.existsByUsername(username)) {
                info = repository.findByUsername(username);
                UserResponse user = new UserResponse();
                user.setAvatar(info.getAvatar());
                user.setEmail(info.getEmail());
                user.setUsername(username);
                user.setId_user(info.getId_user());
                user.setRole(info.getRole());
                user.setGender(info.getGender());
                user.setName(info.getName());
                user.setPhone_number(info.getPhone_number());
                return user;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
