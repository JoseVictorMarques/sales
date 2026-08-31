package com.example.sales.model.dtos;

import com.example.sales.model.entities.Car;
import com.example.sales.model.entities.ModelVersion;
import com.example.sales.model.entities.Transmission;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import static com.example.sales.utils.Base64Utils.base64ToByteArray;

@Getter
@Setter
public class CarDTO {
    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 5, max = 255, message = "Descrição deve ter entre 5 e 255 caracteres")
    private String description;

    @NotBlank(message = "Cor é obrigatória")
    private String color;

    @NotNull(message = "ID da versão do modelo é obrigatório")
    private Long modelVersionId;

    @NotNull(message = "ID da transmissão é obrigatório")
    private Long transmissionId;

    @NotNull(message = "Ano de fabricação é obrigatório")
    @Min(value = 1900, message = "Ano de fabricação deve ser maior que 1900")
    @Max(value = 2100, message = "Ano de fabricação deve ser menor que 2100")
    private Integer manufacturingYear;

    private String imageBase64;

    public Car toEntity(){
        Car car = new Car();
        car.setId(this.id);
        car.setDescription(this.description);
        car.setColor(this.color);
        car.setModelVersion(new ModelVersion(this.modelVersionId));
        car.setTransmission(new Transmission(this.transmissionId));
        car.setManufacturingYear(this.manufacturingYear);
        if(this.imageBase64 != null){
            car.setImage(base64ToByteArray(this.imageBase64));
        }
        return car;
    }
}
