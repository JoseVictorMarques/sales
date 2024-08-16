package com.example.sales.model.dtos;

import com.example.sales.model.entities.Car;
import com.example.sales.model.entities.ModelVersion;
import com.example.sales.model.entities.Transmission;
import lombok.Getter;
import lombok.Setter;

import static com.example.sales.utils.Base64Utils.base64ToByteArray;

@Getter
@Setter
public class CarDTO {
    private Long id;
    private String description;
    private String color;
    private Long modelVersionId;
    private Long transmissionId;
    private Integer manufacturingYear;
    private String imageBase64;

    public Car toEntiy(){
        Car car = new Car();
        car.setId(this.id);
        car.setDescription(this.description);
        car.setColor(this.color);
        car.setModelVersion(new ModelVersion(this.modelVersionId));
        car.setTransmission(new Transmission(this.transmissionId));
        car.setManufacturingYear(this.manufacturingYear);
        if(this.imageBase64 !=null){
            car.setImage(base64ToByteArray(this.imageBase64));
        }
        return car;
    }
}
