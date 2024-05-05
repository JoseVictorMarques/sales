package com.example.sales.business;

import com.example.sales.model.dtos.ModelDTO;
import com.example.sales.model.dtos.ModelVersionDTO;
import com.example.sales.model.entities.Model;
import com.example.sales.model.entities.ModelVersion;
import com.example.sales.repository.ModelVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ModelVersionBusiness {

    @Autowired
    private ModelVersionRepository repository;
    public ModelVersionDTO createModelVersion (ModelVersionDTO modelVersionDTO){
        ModelVersion modelVersion = modelVersionDTO.toEntity();
        modelVersion = repository.save(modelVersion);
        return modelVersion.toDTO();
    }

    public List<ModelVersionDTO> listVersionByModel(Long idModel){
        List<ModelVersion> modelVersionList = repository.listVersionByIdModel(idModel);
        return modelVersionList.stream().map(ModelVersion::toDTO).collect(Collectors.toList());
    }


}
