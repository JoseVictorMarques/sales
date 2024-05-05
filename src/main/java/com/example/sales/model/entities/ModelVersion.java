package com.example.sales.model.entities;


import com.example.sales.model.dtos.ModelDTO;
import com.example.sales.model.dtos.ModelVersionDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name="TB_MODEL_VERSION")
public class ModelVersion implements Serializable {

    private static final long serialVersionUID = 7L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 100)
    private String version;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    public ModelVersionDTO toDTO(){
        ModelVersionDTO modelVersionDTO = new ModelVersionDTO();
        modelVersionDTO.setId(this.id);
        modelVersionDTO.setVersion(this.version);
        modelVersionDTO.setModelId(this.model.getId());
        modelVersionDTO.setModelName(this.model.getName());
        return modelVersionDTO;
    }
}
