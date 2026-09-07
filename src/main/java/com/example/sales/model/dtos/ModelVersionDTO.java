package com.example.sales.model.dtos;

import com.example.sales.model.entities.Model;
import com.example.sales.model.entities.ModelVersion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelVersionDTO {
    Long id;

    @NotBlank(message = "Versão é obrigatória")
    String version;

    @NotNull(message = "ID do modelo é obrigatório")
    Long modelId;

    String modelName;

    public ModelVersion toEntity(){
        ModelVersion modelVersion = new ModelVersion();
        modelVersion.setId(this.id);
        modelVersion.setVersion(this.version);
        modelVersion.setModel(new Model(this.modelId));
        return modelVersion;
    }

}
