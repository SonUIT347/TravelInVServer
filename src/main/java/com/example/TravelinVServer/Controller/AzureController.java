/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Controller;

import com.example.TravelinVServer.Service.AzureBlobStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping(path = "/azure")
public class AzureController {

    @Autowired
    private AzureBlobStorageService azureBlobStorageService;

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            return azureBlobStorageService.uploadImage(file);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
