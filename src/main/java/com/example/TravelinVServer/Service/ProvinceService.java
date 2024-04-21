/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TravelinVServer.Service;

import com.example.TravelinVServer.Modal.Province;
import com.example.TravelinVServer.Repository.ProvinceRepository;
import com.example.TravelinVServer.Responese.PostResponse;
import com.example.TravelinVServer.Responese.ProvinceResponse;
import com.example.TravelinVServer.Until.Helper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private Helper helper;
    public ProvinceResponse handleGetProvinceInfoFromID(Integer id_province) {
        Optional<Province> optionalProvince = provinceRepository.findById(id_province);
        if (optionalProvince.isPresent()) {
            ProvinceResponse res = new ProvinceResponse();
            res.setDescription(optionalProvince.get().getDescription());
            res.setId_province(optionalProvince.get().getId_province());
            res.setImage(optionalProvince.get().getImage());
            res.setProvince_name(optionalProvince.get().getProvince_name());
            return res;
        } else {
            return null;
        }
    }

    public Province getProvinceFromID(Integer id_province) {
        Optional<Province> optionalProvince = provinceRepository.findById(id_province);
        if (optionalProvince.isPresent()) {
            return optionalProvince.get();
        } else {
            return null;
        }
    }

    public List<ProvinceResponse> handleGetAllProvince() {
        List<Province> provinces = provinceRepository.findAll();
        List<ProvinceResponse> responseProvinces = provinces.stream()
                .map(province -> {
                    ProvinceResponse response = new ProvinceResponse();
                    response.setDescription(province.getDescription());
                    response.setId_province(province.getId_province());
                    response.setProvince_name(province.getProvince_name());
                    response.setImage(province.getImage());
                    return response;
                })
                .collect(Collectors.toList());
        return responseProvinces;
    }

    public List<PostResponse> handleGetPostByProvinceID(Integer id_province) {
        try {
            List<PostResponse> result = provinceRepository.getPostByProvinceId(id_province);
            if (result != null && provinceRepository.existsById(id_province)) {
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ProvinceResponse> handleGetDestination(Integer id_province) {
        try {
            if (provinceRepository.existsById(id_province)) {
                List<ProvinceResponse> result = provinceRepository.getDestination(id_province);
                if (result.size() > helper.getSQL_ROWS_LIMIT_5()) {
                    result = result.subList(0, helper.getSQL_ROWS_LIMIT_5());
                }
                return result != null ? result : null;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
