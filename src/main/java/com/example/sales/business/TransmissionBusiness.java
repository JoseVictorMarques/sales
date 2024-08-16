package com.example.sales.business;

import com.example.sales.model.entities.Transmission;
import com.example.sales.repository.TransmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransmissionBusiness {
    @Autowired
    private TransmissionRepository repository;

    public Transmission createTransmission(Transmission transmission){
        return repository.save(transmission);
    }

    public List<Transmission> listTransmission(){
        return repository.findAll();
    }
}
