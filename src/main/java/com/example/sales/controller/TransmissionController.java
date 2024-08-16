package com.example.sales.controller;

import com.example.sales.business.TransmissionBusiness;
import com.example.sales.model.entities.Transmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transmission")
public class TransmissionController {
    @Autowired
    TransmissionBusiness transmissionBusiness;

    @PostMapping("/create-transmission")
    public ResponseEntity<Transmission> createModel(@RequestBody Transmission transmission){
        return ResponseEntity.ok(transmissionBusiness.createTransmission(transmission));
    }

    @GetMapping("/list-transmisison")
    public ResponseEntity<List<Transmission>> listTransmission(){
        return ResponseEntity.ok(transmissionBusiness.listTransmission());
    }
}
