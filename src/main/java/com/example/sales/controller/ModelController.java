package com.example.sales.controller;

import com.example.sales.business.ModelBusiness;
import com.example.sales.model.dtos.ModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("model")
public class ModelController {

    @Autowired
    private ModelBusiness modelBusiness;

    @PostMapping("/create")
    public ResponseEntity<ModelDTO> createModel(@RequestBody ModelDTO modelDTO){
        return ResponseEntity.ok(modelBusiness.createModel(modelDTO));
    }
}
