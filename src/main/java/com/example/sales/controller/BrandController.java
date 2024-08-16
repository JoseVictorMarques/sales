package com.example.sales.controller;

import com.example.sales.business.BrandBusiness;
import com.example.sales.model.entities.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {
    @Autowired
    BrandBusiness brandBusiness;

    @PostMapping("/create")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        return ResponseEntity.ok(brandBusiness.createBrand(brand));
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Brand>> registerUser() {
        return ResponseEntity.ok(brandBusiness.findAllBrands());
    }
}
