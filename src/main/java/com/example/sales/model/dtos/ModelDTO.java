package com.example.sales.model.dtos;

import com.example.sales.model.entities.Brand;
import com.example.sales.model.entities.Model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelDTO {
    Long id;
    String name;
    Long brandId;
    String brandName;

    public Model toEntiy(){
        Model model = new Model();
        model.setId(this.id);
        model.setName(this.name);
        model.setBrand(new Brand(this.brandId));
        return model;
    }

}


