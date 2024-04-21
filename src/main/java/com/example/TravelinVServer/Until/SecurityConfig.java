/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Until;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.TravelinVServer.Jwt.JwtAuthenticationFilter;
import com.example.TravelinVServer.Service.UserService;

/**
 *
 * @author Admin
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private JwtAuthenticationFilter authFilter;

    @Autowired
    public SecurityConfig(JwtAuthenticationFilter authFilter) {
        this.authFilter = authFilter;
    }

    // User Creation 
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }

    // Configuring HttpSecurity 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/welcome", "/auth/login", "/post/public/**", "/public/**", "/azure/**", "like/**", "/description/**", "/comment/**", "comment/post/**", "/post/author/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/user/addNewUser").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/user/info/**", "/comment/create/**", "/comment/delete/**", "/comment/update/**", "/post/sortbystatus", "/user/update", "/like/delete/**").authenticated()
                .and()
                .authorizeHttpRequests().requestMatchers("/admin/**", "/subcomment/create/**", "/subcomment/update/**", "/subcomment/delete/**").authenticated()
                .and()
                .authorizeHttpRequests().requestMatchers("/post/delete/**","/post/createPost", "/post/updatePostStatus/**", "/post/getRelatedPost/**/**").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // Password Encoding 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
