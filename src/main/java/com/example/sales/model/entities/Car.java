package com.example.sales.model.entities;

import com.example.sales.model.dtos.CarDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.example.sales.utils.Base64Utils.byteArrayToBase64;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TB_CAR")
public class Car implements Serializable {

    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String color;

    @ManyToOne
    @JoinColumn(name = "model_version_id")
    private ModelVersion modelVersion;

    @ManyToOne
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    @Column(nullable = false)
    private Integer manufacturingYear;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image")
    private byte[] image;

    public CarDTO toDTO(){
        CarDTO carDTO = new CarDTO();
        carDTO.setId(this.id);
        carDTO.setDescription(this.description);
        carDTO.setColor(this.color);
        carDTO.setModelVersionId(this.modelVersion.getId());
        carDTO.setTransmissionId(this.transmission.getId());
        carDTO.setManufacturingYear(this.manufacturingYear);
        if(this.image != null){
            carDTO.setImageBase64(byteArrayToBase64(this.image));
        }
        return carDTO;
    }
}
