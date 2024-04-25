package com.example.sales.business;

import com.example.sales.model.entities.Brand;
import com.example.sales.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandBusiness  {
    @Autowired
    private BrandRepository brandRepository;

    public Brand createBrand(Brand brand){
        return brandRepository.save(brand);
    }

    public List<Brand> findAllBrands(){
        return brandRepository.findAll();
    }
}
