/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Service;

import com.example.TravelinVServer.Modal.User;
import com.example.TravelinVServer.Repository.UserRepository;
import com.example.TravelinVServer.RequestsDTO.UserRequest;
import com.example.TravelinVServer.Until.CustomUserDetail;
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
            user.setName(userInfo.getName());
            user.setUsername(userInfo.getUsername());
            user.setAvatar(userInfo.getAvatar());
            repository.save(user);
            return "User Added Successfully";
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

    public boolean isUserExists(String username) {
        return repository.existsByUsername(username);
    }

    public User getUserInfo(String username) {
//        System.out.println(repository.findByUsername(username).getUsername());
        return repository.findByUsername(username);
    }
}
