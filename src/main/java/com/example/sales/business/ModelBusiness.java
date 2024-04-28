package com.example.sales.business;

import com.example.sales.model.dtos.ModelDTO;
import com.example.sales.model.entities.Model;
import com.example.sales.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelBusiness {
    @Autowired
    private ModelRepository repository;

    public ModelDTO createModel (ModelDTO modelDTO){
        Model model = modelDTO.toEntiy();
        model = repository.save(model);
        return model.toDTO();
    }

    public List<ModelDTO> listModelsByBrand(Long idBrand){
        List<Model> modelList = repository.listModelByIdBrand(idBrand);
        return modelList.stream().map(Model::toDTO).collect(Collectors.toList());
    }

}
