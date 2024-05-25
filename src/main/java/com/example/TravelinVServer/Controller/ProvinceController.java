/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Controller;

import com.example.TravelinVServer.Responese.PostResponse;
import com.example.TravelinVServer.Responese.ProvinceResponse;
import com.example.TravelinVServer.Service.ProvinceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/public")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/province")
    public ResponseEntity<List<ProvinceResponse>> getAllProvince() {
        try {
            List<ProvinceResponse> ResponseProvinces = provinceService.handleGetAllProvince();
            return ResponseEntity.status(HttpStatus.OK).body(ResponseProvinces);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/province/{id_province}")
    public ResponseEntity<ProvinceResponse> getProvinceInfoById(@PathVariable("id_province") int idProvince) {
        try {
            ProvinceResponse optionalProvince = provinceService.handleGetProvinceInfoFromID(idProvince);
            System.out.println(optionalProvince.getProvince_name());
            return ResponseEntity.ok(optionalProvince);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/province/post/{id_province}")
    public ResponseEntity<List<PostResponse>> getPostByProvinceId(@PathVariable("id_province") int idProvince) {
        try {
            List<PostResponse> optionalProvince = provinceService.handleGetPostByProvinceID(idProvince);
            if (optionalProvince != null) {
                return ResponseEntity.ok(optionalProvince);
            } else {
                return ResponseEntity.badRequest().build();
            }

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/province/destination/{id_province}")
    public ResponseEntity<List<ProvinceResponse>> getDestination(@PathVariable("id_province") int id_province) {
        try {
            List<ProvinceResponse> res = provinceService.handleGetDestination(id_province);
            return res != null ? ResponseEntity.ok(res) : ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
