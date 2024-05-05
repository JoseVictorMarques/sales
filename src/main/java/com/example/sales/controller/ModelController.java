package com.example.sales.controller;

import com.example.sales.business.ModelBusiness;
import com.example.sales.business.ModelVersionBusiness;
import com.example.sales.model.dtos.ModelDTO;
import com.example.sales.model.dtos.ModelVersionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("model")
public class ModelController {

    @Autowired
    private ModelBusiness modelBusiness;
    @Autowired
    private ModelVersionBusiness modelVersionBusiness;

    @PostMapping("/create-model")
    public ResponseEntity<ModelDTO> createModel(@RequestBody ModelDTO modelDTO){
        return ResponseEntity.ok(modelBusiness.createModel(modelDTO));
    }

    @GetMapping("/list-models-by-brand/{idBrand}")
    public ResponseEntity<List<ModelDTO>> listModelsByBrand(@PathVariable Long idBrand ){
        return ResponseEntity.ok(modelBusiness.listModelsByBrand(idBrand));
    }

    @PostMapping("/create-model-version")
    public ResponseEntity<ModelVersionDTO> createModelVersion(@RequestBody ModelVersionDTO modelVersionDTO){
        return ResponseEntity.ok(modelVersionBusiness.createModelVersion(modelVersionDTO));
    }

    @GetMapping("/list-version-by-model/{idModel}")
    public ResponseEntity<List<ModelVersionDTO>> listVersionByModel(@PathVariable Long idModel ){
        return ResponseEntity.ok(modelVersionBusiness.listVersionByModel(idModel));
    }
}
